<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <table border ="4">
            <tr bgcolor="#9acd32">
                <th>Átlag</th>
            </tr>
            <tr>
                <td><xsl:value-of select ="sum(vizsgak_V9RN7C/vizsga/jegy) div count(vizsgak_V9RN7C/vizsga/jegy)"/></td>
            </tr>
        </table>
    </xsl:template>
</xsl:stylesheet>