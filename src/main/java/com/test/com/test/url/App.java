package com.test.com.test.url;

import java.io.IOException;
import java.util.ListIterator;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {

    public static String BASE_URL = "http://pl.wikipedia.org";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Document doc = Jsoup.connect(BASE_URL + "/wiki/Onet.pl").get();
        Elements newsHeadlines = doc.getElementsByAttributeValue("id", "content");
        ListIterator<Element> els = newsHeadlines.listIterator();
        // System.out.println(doc.html());
        // System.out.println(getById(doc, "footer").html());
        out(getByIdStart(doc, "footer-places-").html());
        Elements elems = getByIdStart(doc, "footer-places-");
        String html = elems.outerHtml();
        out(fixAUrl(html, null, BASE_URL));

        out(fixAUrl(elems, BASE_URL).outerHtml());

        validateHrefs(elems);

        while (els.hasNext()) {
            Element el = els.next();

        }
    }

    public static Elements getById(Document doc, String id) {
        return doc.getElementsByAttributeValue("id", id);
    }

    public static Elements getByIdStart(Document doc, String id) {
        return doc.getElementsByAttributeValueStarting("id", id);
    }

    public static void out(Object o) {
        System.out.println(o + "\n\n");
    }

    public static Elements fixAUrl(Elements el, String baseUrl) {
        String ret = "";
        for (Element e : el) {
            // Elements h = e.getElementsByAttribute("href");
            String h = e.children().attr("href");
            if ( h.startsWith("//") ) {
                e.children().attr("href", "http://" + h.substring(2));
            } else if ( h.startsWith("/") ) {
                e.children().attr("href", baseUrl + h);
            }
            // out(e);
        }

        return el;
    }

    public static String fixAUrl(String html, String href, String baseUrl) {
        if ( href == null )
            href = "href=\"";
        return html.replace(href, href + baseUrl);
    }

    public static void validateHrefs(Elements el) {
        out("validateHrefs");
        for (Element e : el) {
            String h = e.children().attr("href");
            out(h);
            Response res = null;
            try {
                res = Jsoup.connect(h).execute();
                if ( res.statusCode() != 200 ) {
                    out("statusCode != 200 !!!!!");
                }
            } catch (IOException e1) {
                out(e1.getMessage());
                // TODO Auto-generated catch block
                // log.warn(e1.getMessage(), e1);
            }

            // try {
            //
            // } catch (MalformedURLException me) {
            // // the URL is not in a valid form
            // // out("the URL is not in a valid form : " + h);
            // } catch (IOException ioe) {
            // // the connection couldn't be established
            // out("the connection couldn't be established " + h);
            // }
        }
    }
}
