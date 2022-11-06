<table class = "table table-bordered table-striped table-hover text-center">
{
let $doc := doc("D:/WorkSpaceJEE/JDK12/WebWebWeb3/WebContent/games.xml")
for $c in distinct-values($doc//plateforme)
return
	<tr>
		<td>
			<a href = "plateformeGames.do?plateforme={data($c)}">{data($c)} </a>
		</td>
	</tr>
}
</table>