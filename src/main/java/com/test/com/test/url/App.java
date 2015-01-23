package com.test.com.test.url;

import java.io.IOException;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Document doc = Jsoup.connect("http://pl.wikipedia.org/wiki/Onet.pl").get();
        Elements newsHeadlines = doc.getElementsByAttributeValue("id", "content");
        ListIterator<Element> els = newsHeadlines.listIterator();
        System.out.println(doc.html());
        while (els.hasNext()) {
            Element el = els.next();

        }
    }
}
