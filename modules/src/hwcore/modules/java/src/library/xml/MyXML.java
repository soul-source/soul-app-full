/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.xml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class MyXML {

    private Document doc = null;
    private Element rootNode = null;
    private File xmlFile = null;

    public Element getRootNode() {
        return rootNode;
    }

    public void setRootNode(Element rootNode) {
        this.rootNode = rootNode;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public MyXML(String filePath) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            xmlFile = new File(filePath);
            //XXX workaround (?)
            builder.setFeature("http://xml.org/sax/features/validation", false);
            builder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            builder.setValidation(false);
            doc = builder.build(xmlFile);
            rootNode = doc.getRootElement();
        } catch (JDOMException ex) {
            Logger.getLogger(MyXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Element getFirstElementByTag(String tag, Element parentNode) {
        List children = parentNode != null ? parentNode.getChildren(tag) : rootNode.getChildren(tag);
        return children.iterator().hasNext() ? (Element) children.iterator().next() : null;
    }

    public ArrayList<Element> getElementsByTag(String tag, Element parentNode) {
        List children = parentNode != null ? parentNode.getChildren(tag) : rootNode.getChildren(tag);
        Iterator iterator = children.iterator();
        ArrayList<Element> elem = new ArrayList<Element>();
        while (iterator.hasNext()) {
            elem.add((Element) iterator.next());
        }

        return elem;
    }

    /**
     *
     * @param elem
     * @param tagFilter to get only values of certain subtag, leave null to get
     * all values
     * @return
     */
    public ArrayList<String> getElementsValues(ArrayList<Element> elem, String tagFilter) {

        ArrayList<String> values = new ArrayList<String>();
        int i = 0;
        for (Element E : elem) {
            if (tagFilter != null) {
                if (E.getName().equalsIgnoreCase(tagFilter)) {
                    values.add(E.getValue());
                    i++;
                }
            } else {
                values.add(E.getValue());
                i++;
            }

        }
        return values;
    }

    /**
     *
     * @param tag the tag name of new element
     * @param value value to write inside new tag, if value is an empty string
     * (""), the check of duplicate is only on tag
     * @param attributes attributes to write inside new tag, leave null if no
     * attributes needed
     * @param parent the parent element where start to check/write inside
     * @param checkDuplicate check if the specified new element is already
     * present inside the file and return it
     * @return the new element of the document, return null in error case
     */
    public Element writeElement(String tag, String value, Collection attributes, Element parent, boolean checkDuplicate) {
        try {
            Element el = parent != null ? parent : doc.getRootElement();
            if (checkDuplicate) {
                Element chkel = getFirstElementByTag(tag, el);
                if (chkel != null && (value.isEmpty() || chkel.getValue().equals(value))) {
                    return chkel;
                }
            }

            Element newEl = new Element(tag);
            if (attributes != null) {
                newEl.setAttributes(attributes);
            }
            newEl.setText(value);
            el.addContent(newEl);

            FileWriter fileWriter = new FileWriter(xmlFile);
            //Creazione dell'oggetto XMLOutputter 
            XMLOutputter outputter = new XMLOutputter();
            //Imposto il formato dell'outputter come "bel formato" 
            outputter.setFormat(Format.getPrettyFormat());
            //Produco l'output sul file
            outputter.output(doc, fileWriter);
            return el.getChild(tag);
        } catch (IOException ex) {
            Logger.getLogger(MyXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
