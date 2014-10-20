dashboard_customer(contextPath, errorMessage)::="
<div class='container'>
	This is customer Dashboard page.
	$if(errorMessage)$
		<p>$errorMessage$</p>
	$endif$
</div>
";