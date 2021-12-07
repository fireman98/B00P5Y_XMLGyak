<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>autok1B00P5Y</h2>
                <table border="1" cellpadding="5px">
                    <tr bgcolor="#9acd32">
                        <th>A dokumentum elemeinek száma</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="count(//*)" />
                        </td>
                    </tr>
                </table>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>