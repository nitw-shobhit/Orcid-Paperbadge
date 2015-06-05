<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Paper-Badge</title>
<style type="text/css">
	.mainBoxSt {
		background : url(<%= request.getContextPath()%>/resources/images/bg.jpg) no-repeat center center fixed;
		color: #FFF;
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
		height: 900px;
		width: 1200px; 
	}
	.inputBox {
		border: none;
		border-bottom: solid 2px #c9c9c9;
		transition: border 0.3s;
	}
	.button-gen {
		background-color: #FFF;
		height: 40px;
		font-size: 15px;
		font-weight: bold;
	}
	.button-gen:hover {
		background-color: #000;
		height: 40px;
		font-size: 15px;
		font-weight: bold;
		color: #FFF;
	}
	.logoBox {
		float: left;
		margin-left: 5px;
		margin-top: 5px;
	}
</style>
<script type="text/javascript">
	function addEmail() {
		var oldText = document.getElementById('emailBox').value;
		var newText = oldText + document.getElementById('c_emailAddr').value + ",";
		document.getElementById('emailBox').value = newText;
		document.getElementById('emailBox').style.display = 'block';
		document.getElementById('c_emailAddr').value = '';
	}
</script>
</head>
<body>
	<center>
		<div id="mainBox" class="mainBoxSt">
			<div class="logoBox">
				<img src="<%= request.getContextPath()%>/resources/images/logo.PNG" />
			</div>
			<br><br><br><br><br><br><br><br>
			<h1>CLAIM YOUR BADGES TODAY!!!</h1>
			<br><br><br><br>
			<div style="border: 1px solid gray; font-size: 20px; width: 700px;">
				<br>
				Please enter the following details<br><br>
				<form:form method="POST" action="/paper-badge-web/pbMain/sendBadgeLink.do">
					<table>
						<tr>
							<td style="vertical-align: bottom;">
								DOI :&nbsp;
							</td>
							<td style="vertical-align: bottom;">
								<input type="text" placeholder="Enter DOI" class="inputBox" name="doi" />
							</td>
						</tr>
						<tr>
							<td style="vertical-align: bottom;">
								Email Address :&nbsp;
							</td>
							<td style="vertical-align: bottom;">
								<input type="text" placeholder="Enter your Email Address" class="inputBox" name="emailAddr" />
							</td>
						</tr>
						<tr>
							<td style="vertical-align: bottom;">
								Orcid(Optional) :&nbsp;
							</td>
							<td style="vertical-align: bottom;">
								<input type="text" placeholder="Enter your Orcid id" class="inputBox" name="orcid" />
							</td>
						</tr>
						<tr>
							<td style="vertical-align: bottom;">
								Add an author/contributor? :&nbsp;
							</td>
							<td style="vertical-align: bottom;">
								<textarea id="emailBox" name="contributors" rows="2" cols="40" style="border: 1px solid; display: none;" disabled="disabled"></textarea>
								<input type="text" placeholder="Email Address" class="inputBox" id="c_emailAddr" />&nbsp;/&nbsp;
								<a onclick="addEmail()"><img src="<%= request.getContextPath()%>/resources/images/add.ico" width="16" height="16" /></a>
							</td>
						</tr>
					</table>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="submit" value="Give ME my BADGE!" class="button-gen" />
					<br><br>
				</form:form>
			</div>
			<br><br>
			<marquee behavior="scroll" direction="left" onmouseover="this.stop();" onmouseout="this.start();" style="cursor: pointer;">Claiming a badge requires you to have an Orcid	. Learn about orcid @<a href="http://orcid.org/" target="_blank" style="cursor: pointer; color: #FFF;">http://orcid.org/</a></marquee>
			<br><br>
			<hr style="width: 300px;">
			<div>
				Visit Us @<a href="http://openbadges.org/" target="_blank" style="cursor: pointer; color: #FFF;">http://openbadges.org/</a>
			</div>
		</div>
	</center>
</body>
</html>