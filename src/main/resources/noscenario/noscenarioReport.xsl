<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:in="http://www.xoev.de/de/validator/framework/1/createreportinput"
                xmlns:no="http://www.xoev.de/de/validator/framework/1/noscenarioreport"
                exclude-result-prefixes="xs in"
                version="2.0">

    <xsl:output method="xml" indent="yes" />

    <xsl:param name="input-document" as="document-node(element())" required="yes" />

    <xsl:template match="in:createReportInput">
        <no:NoScenarioFound frameworkVersion="1.2.0">
            <xsl:apply-templates select="in:engine" mode="copy-to-report-ns" />
            <xsl:apply-templates select="in:timestamp" mode="copy-to-report-ns" />
            <xsl:apply-templates select="in:documentIdentification" mode="copy-to-report-ns" />
            <xsl:apply-templates select="in:processingError" mode="copy-to-report-ns" />
            <no:message>The document does not match any of the configured scenarios of this validator instance.</no:message>
        </no:NoScenarioFound>
    </xsl:template>

    <xsl:template mode="copy-to-report-ns" match="element()" priority="5">
        <xsl:element name="no:{local-name()}">
            <xsl:apply-templates mode="copy-to-report-ns" select="node()|@*" />
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>