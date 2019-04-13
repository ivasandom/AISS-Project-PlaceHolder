<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<html>
  <head>
    <meta name="google-site-verification" content="_NmfhoumLzs-Z5B1TMC85vXgfkkzMEjuwNivJBGcPqQ" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello World</title>
  </head>

  <body>
    <h1>Hello World!</h1>
	
    <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Available Servlets:</td>        
      </tr>
      <tr>
        <td><a href="helloworld">HelloWorld</a></td>
      </tr>
    </table>
    <h2> Tokens de OAuth </h2>
        <ul>
            <li><strong>GitHub:</strong>
                <ul>
                    <li><c:out value='${sessionScope["GitHub-token"]}' /> </li>
                </ul>
        </ul>
  </body>
</html>
