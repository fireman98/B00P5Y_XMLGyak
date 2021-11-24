<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>autokB00P5Y1</h2>
                <table border="1" cellpadding="5px">
                    <tr bgcolor="#9acd32">
                        <th>Rendszám</th>
                        <th>Ár</th>
                    </tr>
                    <xsl:for-each select="autok/auto">
                        <xsl:sort select="ar" />
                        <tr>
                            <td>
                                <xsl:value-of select="@rsz" />
                            </td>
                            <td>
                                <xsl:value-of select="ar" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>