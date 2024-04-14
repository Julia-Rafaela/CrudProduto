<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value ="./resources/css/styles.css"/>'>
 <title>Produto</title>
</head>
<body>
<div>
    <jsp:include page="menu.jsp" />
</div>
<br />
<div align="center" class="container">
    <form action="produto" method="post">
        <p class="title">
            <b>Produto</b>
        </p>
        <table>
            <tr>
                <td colspan="3">
                    <input class="input_data_id" type="number"
                           min="0" step="1" id="codigo" name="codigo" placeholder="Codigo"
                           value='<c:out value="${produto.codigo}"/>'>
                </td>
                <td>
                    <input type="submit" id="botao" name="botao"
                           value="Buscar">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <input class="input_data" type="text"
                           id="nome" name="nome" placeholder="Nome"
                           value='<c:out value="${produto.nome}"/>'>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <input class="input_data" type="text"
                           id="valorUnitario" name="valorUnitario" placeholder="Valor Unitário"
                           value='<c:out value="${produto.valorUnitario}"/>'>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <input class="input_data" type="number"
                           min="0" step="1" id="quantidade" name="quantidade" placeholder="Quantidade"
                           value='<c:out value="${produto.quantidade}"/>'>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="botao" name="botao"
                           value="Cadastrar">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao"
                           value="Alterar">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao"
                           value="Listar">
                </td>
            </tr>
        </table>
    </form>
</div>
<br />
<div align="center">
    <c:if test="${not empty erro}">
        <h2><b><c:out value="${erro}"/></b></h2>
    </c:if>
</div>
<div align="center">
    <c:if test="${not empty saida}">
        <h2><b><c:out value="${saida}"/></b></h2>
    </c:if>
</div>
<br/>
<div align="center">
    <c:if test="${not empty produtos}">
        <table border="1">
            <thead>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Valor Unitário</th>
                <th>Quantidade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${produtos}">
                <tr>
                    <td><c:out value="${p.codigo}"/></td>
                    <td><c:out value="${p.nome}"/></td>
                    <td><c:out value="${p.valorUnitario}"/></td>
                    <td><c:out value="${p.quantidade}"/></td>
                    <td><a href="produto?cmd=alterar&codigo=${p.codigo}">ALTERAR</a></td>
                    <td><a href="produto?cmd=excluir&codigo=${p.codigo}">EXCLUIR</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
