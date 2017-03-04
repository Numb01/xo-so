<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${region=='MB' && region!=null && listLottery!=null}">
    <c:forEach items="${listLottery}" var="lottery" begin="0" end="0" varStatus="statusLottery">
        <div class="Left_background">                                             
            <div class="Left_BANGKQ_right">
                <div class="Centre_DAUDUOI_table">
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                        <tr class="Centre_DAUDUOI_table_row_1 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_1</c:if>">
                                <td style="text-align:center;">Đầu</td>
                                <td style="text-align:left; padding:0 0 0 10px;">LOTO</td>
                            </tr>
                        <c:forEach items="${listDuoi}"  var="duoi" varStatus="status">
                            <tr class="Centre_DAUDUOI_table_row_2 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_3</c:if> ">
                                <td class="Centre_DAUDUOI_table_col_1 do <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_1</c:if> ">${status.index}</td>
                                <td class="Centre_DAUDUOI_table_col_2"><b>${duoi}</b></td>
                            </tr>
                        </c:forEach>                                    
                    </table>
                </div>
            </div>
            <div class="both"></div>
        </div>
    </c:forEach>               
</c:if>
<c:if test="${lotterys!=null && region=='MT'}">
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
                        <td class="TableKQMN_DAUDUOI_col_2 TableKQMB_row_2"><b>${duoi}</b></td>
                    </c:forTokens>                                    
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${lotterys!=null &&region=='MN'}">
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
                        <td class="TableKQMN_DAUDUOI_col_2 TableKQMB_row_2"><b>${duoi}</b></td>
                    </c:forTokens>                                    
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>


