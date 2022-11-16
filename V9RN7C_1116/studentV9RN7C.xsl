<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <table border ="4">
            <tr bgcolor="#9acd32">
                <th>ID</th>
                <th>vezeteknev</th><th>keresztnev</th>
                <th>becenev</th>
                <th>kor</th>
                <th>osztondij</th>
            </tr>
    <!-- for-each feldolgozási utasitás. Megkeresi az XPath kifejezésnek-->
            <xsl:for-each select="class/student">
                <tr>
                    <td>
                    <!-- value-of proceesing instruction. Az XPath kifejezésnek-->
                        <xsl:value-of select = "@id"/>
                    </td>
                    <td><xsl:value-of select = "vezeteknev"/></td>
                    <td><xsl:value-of select = "keresztnev"/></td>
                    <td><xsl:value-of select = "becenev"/></td>
                    <td><xsl:value-of select = "kor"/></td>
                    <td><xsl:value-of select = "osztondij"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>