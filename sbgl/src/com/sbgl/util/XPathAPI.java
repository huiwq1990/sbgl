package com.sbgl.util;


import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.apache.xalan.xpath.XPathSupport;
import org.apache.xalan.xpath.XPath;
import org.apache.xalan.xpath.XPathProcessorImpl;
import org.apache.xalan.xpath.xml.XMLParserLiaisonDefault;
import org.apache.xalan.xpath.xml.PrefixResolverDefault;
import org.apache.xalan.xpath.XObject;

public class XPathAPI
{
  public static Node selectSingleNode(Node contextNode, String str)
    throws SAXException
  {
    return selectSingleNode(contextNode, str, contextNode);
  }
  public static Node selectSingleNode(Node contextNode, String str, Node namespaceNode)
    throws SAXException
  {
    NodeList nl = selectNodeList(contextNode, str, namespaceNode);
    return (nl.getLength() > 0) ? nl.item(0) : null;
  }
  public static NodeList selectNodeList(Node contextNode, String str)
    throws SAXException
  {
    return selectNodeList(contextNode, str, contextNode);
  }
  public static NodeList selectNodeList(Node contextNode, String str, Node namespaceNode)
    throws SAXException
  {
    XObject list = eval(contextNode, str, namespaceNode);
    return list.nodeset();

  }
  public static XObject eval(Node contextNode, String str)
    throws SAXException
  {
    return eval(contextNode, str, contextNode);
  }
  public static XObject eval(Node contextNode, String str, Node namespaceNode)
    throws SAXException
  {
    XPathSupport xpathSupport = new XMLParserLiaisonDefault();

    if(null == namespaceNode)
      namespaceNode = contextNode;
    PrefixResolverDefault prefixResolver = new PrefixResolverDefault((namespaceNode.getNodeType() == Node.DOCUMENT_NODE)
                                                         ? ((Document)namespaceNode).getDocumentElement() :
                                                           namespaceNode);
    XPath xpath = new XPath();

    XPathProcessorImpl parser = new XPathProcessorImpl(xpathSupport);
    parser.initXPath(xpath, str, prefixResolver);

    return xpath.execute(xpathSupport, contextNode, prefixResolver);
  }
}
