package net.one97.fetchurl;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainEntry {
	static HashSet<String> hs = new HashSet<String>();

	public static void main(String[] args) {
		fetchUrl("https://www.paytm.com");
		System.out.println("I am done");
	}

	public static void fetchUrl(String url) {
		if (hs.contains(url))
			return;
		else
			hs.add(url);
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
		}
		Elements links = null;
		Elements imports = null;
		try {
			links = doc.select("a[href]");
			imports = doc.select("link[href]");
		} catch (Exception ex) {
		}

		if (imports != null)
			for (Element link : imports) {
					System.out.println(link.attr("abs:href"));
					fetchUrl(link.attr("abs:href"));
			}

		if (links != null)
			for (Element link : links) {
					System.out.println(link.attr("abs:href"));
					fetchUrl(link.attr("abs:href"));
			}
	}
}
