dashboard_customer(contextPath, errorMessage, user)::="
<div class='container'>
	This is customer Dashboard page.
	$if(errorMessage)$
		<p>$errorMessage$</p>
	$else$
		<p>$user.firstname$</p>
		<p>$user.lastname$</p>
	$endif$
</div>
";