<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>${produto.titulo} - Casa do C�digo</title>

    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>

    <link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"  />    
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/book-collection.css" rel="stylesheet" type="text/css"  media="all"  />
    <link href="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/css/checkout-style.css" rel="stylesheet" type="text/css"  media="all"  />    
</head>
<body>

<header id="layout-header">
    <div class="clearfix container">
        <a href="${s:mvcUrl('HC#index').build()}" id="logo">
            <img src="https://cdn.rawgit.com/alura-cursos/spring-mvc-i-criando-aplicacoes-web-master/master/src/main/webapp/resources/imagens/cdc-logo.svg" />
        </a>
        <div id="header-content">
            <nav id="main-nav">
                <ul class="clearfix">
                    <li>
                        <a href="${s:mvcUrl('CCC#itens').build()}" rel="nofollow"> 
                            <fmt:message key="menu.carrinho">
            					<fmt:param value="${carrinhoCompras.quantidade}"/>
       						</fmt:message>
                        </a>
                    </li>
                    <li>
                        <a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">
                            <fmt:message key="menu.sobre"/>
                        </a>
                    </li>
                    <li>
					    <a href="?locale=pt" rel="nofollow">
					        <fmt:message key="menu.pt"/>
					    </a>
					</li>
					
					<li>
					    <a href="?locale=en_UR" rel="nofollow">
					        <fmt:message key="menu.en"/>
					    </a>
					</li>
                    <li><a href="/pages/perguntas-frequentes" rel="nofollow">Perguntas Frequentes</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<nav class="categories-nav">
    <ul class="container">
        <li class="category">
            <a href="http://www.casadocodigo.com.br">
                <fmt:message key="navegacao.categoria.home"/>
            </a>
        </li>
        <li class="category">
            <a href="/collections/livros-de-agile">
                <fmt:message key="navegacao.categoria.agile"/>
            </a>
        </li>
        <li class="category">
            <a href="/collections/livros-de-front-end">
                <fmt:message key="navegacao.categoria.front_end"/>
            </a>
        </li>
        <li class="category">
            <a href="/collections/livros-de-games">
                <fmt:message key="navegacao.categoria.games"/>
            </a>
        </li>
        <li class="category">
            <a href="/collections/livros-de-java">
                <fmt:message key="navegacao.categoria.java"/>
            </a>
        </li>
        <li class="category">
            <a href="/collections/livros-de-mobile">
                <fmt:message key="navegacao.categoria.mobile"/>
            </a>
        </li>
        <li class="category">
            <a    href="/collections/livros-desenvolvimento-web">
                <fmt:message key="navegacao.categoria.web"/>
            </a>
        </li>
        
    </ul>
</nav>