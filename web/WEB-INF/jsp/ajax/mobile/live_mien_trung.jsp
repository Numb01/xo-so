<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${lotterys!=null}">
    <div class="Left_background">
        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1 do">Tỉnh</td>
                <c:forTokens items="${lotterys.province}" delims="+" var="province">
                    <td class="TableKQMN_col_3 TableKQMB_row_2"><span class="do">${province}</span></td>
                    </c:forTokens>                                
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G8</td>
                <c:forTokens items="${lotterys.eighth}" delims="+" var="eighth">
                    <td class="TableKQMN_col_2">${eighth}</td>
                </c:forTokens>     
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G7</td>
                <c:forTokens items="${lotterys.seventh}" delims="+" var="seventh">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">${seventh}</td>
                </c:forTokens>     
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G6</td>
                <c:forTokens items="${lotterys.sixth}" delims="+" var="sixthDelim">
                    <td class="TableKQMN_col_2">
                        <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                            ${sixth}<br/>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G5</td>
                <c:forTokens items="${lotterys.fifth}" var="fifth" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">${fifth}</td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G4</td>
                <c:forTokens items="${lotterys.fourth}" delims="+" var="fourthDelim">
                    <td class="TableKQMN_col_2">
                        <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                            ${fourth}<br/>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G3</td>
                <c:forTokens items="${lotterys.third}" delims="+" var="thirdDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${thirdDelim}" delims="-" var="third">
                            ${third}<br/>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G2</td>
                <c:forTokens items="${lotterys.second}" var="second" delims="+">
                    <td class="TableKQMN_col_2">${second}</td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">G1</td>
                <c:forTokens items="${lotterys.first}" var="fist" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">${fist}</td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">ĐB</td>
                <c:forTokens items="${lotterys.special}" var="special" delims="+">
                    <td class="TableKQMN_col_2 do">${special}</td>
                </c:forTokens>
            </tr>
        </table>
    </div>
    <div class="Left_background_2">
        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_DAUDUOI_col_1"><strong>ĐẦU</strong></td>
                <c:forTokens items="${lotterys.province}" var="province" delims="+">
                    <td class="TableKQMN_DAUDUOI_col_2"><strong>${province}</strong></td>
                        </c:forTokens>
            </tr>
            <c:forEach items="${listDuoi}" var="duois" varStatus="status">
                <tr class="TableKQMB_row_1">
                    <td class="TableKQMN_DAUDUOI_col_1">${status.index}</td>
                    <c:forTokens items="${duois}" delims="+" var="duoi">
                        <td class="TableKQMN_DAUDUOI_col_2 TableKQMB_row_2">${duoi}</td>
                    </c:forTokens>                                    
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>