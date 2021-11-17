<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml"></xsl:output>    
<xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>Bujnóczki Bence</h2>
                <table border="1" cellpadding="5px" >
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Típus</th>
                        <th>Tárgy</th>
                        <th colspan="3">Időpont</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                    </tr>
                    <xsl:for-each select="orarend/ora">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="@tipus" />
                            </td>
                            <td>
                                <xsl:value-of select="targy" />
                            </td>
                            <td>
                                <xsl:value-of select="idopont/nap" />
                            </td>
                            <td>
                                <xsl:value-of select="idopont/tol" />
                            </td>
                            <td>
                                <xsl:value-of select="idopont/ig" />
                            </td>
                            <td>
                                <xsl:value-of select="helyszin" />
                            </td>
                            <td>
                                <xsl:value-of select="oktato" />
                            </td>
                            <td>
                                <xsl:value-of select="szak" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>