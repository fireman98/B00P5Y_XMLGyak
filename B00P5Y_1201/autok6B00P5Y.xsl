<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>autok6B00P5Y</h2>
                <table border="1" cellpadding="5px">
                    <tr bgcolor="#9acd32">
                        <th>Város</th>
                        <th>db</th>
                        <th>Átlagár</th>
                    </tr>
                    <xsl:for-each-group select="autok/auto" group-by="tulaj/varos">
                        <tr>
                            <td>
                                <xsl:value-of select="current-grouping-key()" />
                            </td>
                            <td>
                                <xsl:value-of select="count(current-group())" />
                            </td>
                            <td>
                                <xsl:value-of select="avg(current-group()/ar)" />
                            </td>
                        </tr>
                    </xsl:for-each-group>
                </table>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>