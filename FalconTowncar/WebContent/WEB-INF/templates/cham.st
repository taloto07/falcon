cham(contextPath, image, user, users)::="
<div class='container'>
	<div class='row'>
		<img src='$contextPath$img/$image$' />
	</div>
	
	<div class='row'>
		Id: $user.id$ <br/>
		First Name : $user.firstname$ <br/>
		User Id: $user.lastname$ <br/>
		User Id: $user.email$ <br/>
	</div>
	
	<div class='row'><br/></div>
	
	<div class='row'>
		<!-- Table -->
		<table class='table table-striped'>
			<tr>
				<td>id</td>
				<td>firstname</td>
				<td>lastname</td>
				<td>email</td>
			</tr>
			$users:{n|
				<tr>
					<td>$n.id$</td> 
					<td>$n.firstname$</td>
					<td>$n.lastname$</td>
					<td>$n.email$ </td>
				</tr>
			}$
		</table>
	</div>
</div>
";