<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${lotterys!=null}">
    <div class="Left_BANGKQ_title">
        KẾT QUẢ XỔ SỐ MIỀN TRUNG NGÀY ${lotterys.openDate}
    </div>
    <div class="Left_background">
        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1 do">Tỉnh</td>
                <c:forTokens items="${lotterys.province}" delims="+" var="province">
                    <td class="TableKQMN_col_3 TableKQMB_row_2"><span class="do">${province}</span></td>
                    </c:forTokens>                                
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI TÁM</td>
                <c:forTokens items="${lotterys.eighth}" delims="+" var="eighth">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${eighth}</span></td>
                        </c:forTokens>     
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI BẢY</td>
                <c:forTokens items="${lotterys.seventh}" delims="+" var="seventh">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${seventh}</span></td>
                        </c:forTokens>     
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI SÁU</td>
                <c:forTokens items="${lotterys.sixth}" delims="+" var="sixthDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                            <span>${sixth}</span>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI NĂM</td>
                <c:forTokens items="${lotterys.fifth}" var="fifth" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${fifth}</span></td>
                        </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI TƯ</td>
                <c:forTokens items="${lotterys.fourth}" delims="+" var="fourthDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                            <span>${fourth}<br/></span>
                            </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI BA</td>
                <c:forTokens items="${lotterys.third}" delims="+" var="thirdDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${thirdDelim}" delims="-" var="third">
                            <span>${third}</span>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI NHÌ</td>
                <c:forTokens items="${lotterys.second}" var="second" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${second}</span></td>
                        </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI NHẤT</td>
                <c:forTokens items="${lotterys.first}" var="fist" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${fist}</span></td>
                        </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">ĐẶC BIỆT</td>
                <c:forTokens items="${lotterys.special}" var="special" delims="+">
                    <td class="TableKQMN_col_2 do"><span>${special}</span></td>
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
                        <td class="TableKQMN_DAUDUOI_col_2 TableKQMB_row_2"><b>${duoi}</b></td>
                    </c:forTokens>                                    
                </tr>
            </c:forEach>
        </table>
    </div>
    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <!-- LVS336x280 -->
    <ins class="adsbygoogle"
         style="display:inline-block;width:336px;height:280px"
         data-ad-client="ca-pub-2360743497940079"
         data-ad-slot="7087078346"></ins>
    <script>
        (adsbygoogle = window.adsbygoogle || []).push({});
    </script>
</c:if>