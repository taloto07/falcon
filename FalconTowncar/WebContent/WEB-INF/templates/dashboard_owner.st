dashboard_owner(contextPath, errorMessage)::="
<div class='container'>
	This is Owner Dashboard page.
	$if(errorMessage)$
		<p>$errorMessage$</p>
	$endif$
</div>
";