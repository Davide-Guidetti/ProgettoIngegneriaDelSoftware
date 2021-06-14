<?xml version='1.0' encoding='ISO-8859-1'?>

<%@ page session="false"%>

<%
// QUESTO E' IMPORTANTISSIMO AFFINCHE' L'INTERPRETE JAVASCRIPT RICONOSCA IL CONTENUTO COME XML!!!!!!
response.setHeader("Content-Type","application/xml");

%>


<rss version='2.0'>
	<channel>
		<item>
			<title><![CDATA[<%="aa"%>]]></title>
			<description><![CDATA[<%="bb"%>]]></description>
			<author><![CDATA[<%="cc"%>]]></author>
			<category><![CDATA[<%="dd"%>]]></category>
			<pubDate><![CDATA[<%="ee"%>]]></pubDate>
			<link><![CDATA[<%="ff"%>]]></link>
		</item>
		<item>
			<title><![CDATA[<%="aA"%>]]></title>
			<description><![CDATA[<%="bB"%>]]></description>
			<author><![CDATA[<%="cC"%>]]></author>
			<category><![CDATA[<%="dD"%>]]></category>
			<pubDate><![CDATA[<%="eE"%>]]></pubDate>
			<link><![CDATA[<%="fF"%>]]></link>
		</item>

	</channel>
</rss>
