<%-- 
    Document   : top
    Created on : Feb 27, 2015, 11:04:00 AM
    Author     : iNET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="bg_header">
    <div class="header">
        <a href="/"><img src="/img/Xo-so-wap-logo-xo-so-wap_1.png" alt="Xo-so-wap-logo-xo-so-wap"/></a>
    </div>
</div>   
<div class="menu_bg">
    <div class="menu">
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="${strUrl}/ket-qua-xo-so-truc-tiep.html">Trực Tiếp</a></li>
            <li><a href="${strUrl}/xsmb-ket-qua-xo-so-mien-bac-sxmb-hom-nay.html">XSMB</a></li>
            <li><a href="${strUrl}/xsmt-ket-qua-xo-so-mien-trung-sxmt-hom-nay.html">XSMT</a></li>
            <li><a href="${strUrl}/xsmn-ket-qua-xo-so-mien-nam-sxmn-hom-nay.html">XSMN</a></li>
        </ul>
        <div class="both"></div>
    </div>
</div>
<div class="LMTTODAY_title">
    <h1 style="text-align: center;">${strH1}</h1>
</div>
<div class="LMTTODAY_box">
    <ul>
        <li>
            <div class="LMTTODAY_box_text">
                <p>XỔ SỐ MIỀN BẮC</p>
                <a href="/xsmb-ket-qua-xo-so-mien-bac-sxmb-hom-nay.html">XS Miền Bắc</a>
            </div>
        </li>
        <li>
            <div class="LMTTODAY_box_text">
                <p>XỔ SỐ MIỀN NAM</p>
                <c:if test="${listLotteryCompanyMNOpen!=null}">
                    <c:forEach items="${listLotteryCompanyMNOpen}" var="company">
                        <a href="${strUrl}/${company.codeLowerCase}-ket-qua-xo-so-${company.companyLink}-${company.codeReverseLowerCase}.html" title="kết quả xổ số ${company.company}">XS ${company.company} </a><br>
                    </c:forEach>                                    
                </c:if>                                	
            </div>
        </li>
        <li>
            <div class="LMTTODAY_box_text">
                <p>XỔ SỐ MIỀN TRUNG</p>
                <c:if test="${listLotteryCompanyMTOpen!=null}">
                    <c:forEach items="${listLotteryCompanyMTOpen}" var="company">
                        <c:set var="link" value="${company.companyLink}"/>
                        <c:if test="${company.codeLowerCase=='xstth'}"><c:set var="link" value="thua-thien-hue"/></c:if>
                        <c:if test="${company.codeLowerCase=='xsbdh'}"><c:set var="link" value="binh-dinh"/></c:if>
                        <a href="${strUrl}/${company.codeLowerCase}-ket-qua-xo-so-${link}-${company.codeReverseLowerCase}.html" title="kết quả xổ số ${company.company}">XS ${company.company} </a><br>
                    </c:forEach>                                    
                </c:if> 
            </div>
        </li>
    </ul>
    <div class="both"></div>
</div>
