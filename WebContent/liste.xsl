
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">


		<html>
			<body>
				<h2>Liste des jeux</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>ID</th>
						<th>Titre</th>
						<th>Prix</th>
						<th>L'editeur</th>
						<th>platforme</th>
					</tr>
					<!-- for-each processing instruction Looks for each element matching 
						the XPath expression -->
					<xsl:for-each select="/games/game">
						<tr>
							<td>
								-->
								<xsl:value-of select="@id" />
							</td>
							<td>
								<xsl:value-of select="title" />
							</td>
							<td>
								<xsl:value-of select="price" />
							</td>
							<td>
								<xsl:value-of select="publisher" />
							</td>
							<td>
								<xsl:value-of select="plateforme" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
