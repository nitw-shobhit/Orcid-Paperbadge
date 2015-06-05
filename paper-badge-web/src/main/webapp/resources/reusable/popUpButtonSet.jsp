<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Business Process System</title>
<style type="text/css">
	.button-gen {
		background-color: #FFF;
	}
	.button-gen:hover {
		background-color: #000;
		color: #FFF;
	}
</style>
<script type="text/javascript">
	function closePopup() {
		$(this).dialog("close");
	}
</script>
</head>
<body>
	<center>
		<table cellspacing="5">
			<tr>
				<td>
					<input type="submit" value="Confirm" class="button-gen" />
				</td>
				<td>
					<input type="button" onclick="closePopup()" value="Cancel" class="button-gen" />
				</td>
			</tr>
		</table>
	</center>
</body>
</html>