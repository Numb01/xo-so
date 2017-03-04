<%-- 
    Document   : live
    Created on : Mar 4, 2016, 3:01:47 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${region=='MB' && region!=null && listLottery!=null}">
    <c:forEach items="${listLottery}" var="lottery" begin="0" end="0" varStatus="statusLottery">
        <div class="Left_BANGKQ_title">
            KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}
        </div>
        <div class="Left_background">
            <div class="Left_BANGKQ_left">
                <div class="Centre_BANGKQ_bg">
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1 do" <c:if test="${lottery.special_xsmb!=null}"> rowspan="2" </c:if> >ĐẶC BIỆT</td>
                            <td class="Centre_BANGKQ_table_col_2 do" colspan="12">${lottery.special}</td>
                        </tr>
                        <c:if test="${lottery.special_xsmb!=null}">
                            <tr class="Centre_BANGKQ_table_bg">
                                <td class="Centre_BANGKQ_table_col_2 do" colspan="12"><span>${lottery.special_xsmb}</span></td>
                            </tr>
                        </c:if>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td class="Centre_BANGKQ_table_col_1">GIẢI NHẤT</td>
                            <td class="Centre_BANGKQ_table_col_2" colspan="12"><span>${lottery.first}</span></td>
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1">GIẢI NHÌ</td>
                            <c:forTokens items="${lottery.second}" var="second" delims="-">
                                <td class="Centre_BANGKQ_table_col_2" colspan="6"><span>${second}</span></td>
                            </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td rowspan="2" class="Centre_BANGKQ_table_col_1">GIẢI BA</td>
                            <c:forTokens items="${lottery.third}" var="third" delims="-" begin="0" end="2">
                                <td class="Centre_BANGKQ_table_col_2" colspan="4"><span>${third}</span></td>
                            </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <c:forTokens items="${lottery.third}" var="third" delims="-" begin="3" end="5">
                                <td class="Centre_BANGKQ_table_col_2" colspan="4"><span>${third}</span></td>
                            </c:forTokens>                                         
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1">GIẢI TƯ</td>
                            <c:forTokens items="${lottery.fourth}" var="fourth" delims="-" >
                                <td class="Centre_BANGKQ_table_col_2" colspan="3"><span>${fourth}</span></td>
                            </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td rowspan="2" class="Centre_BANGKQ_table_col_1">GIẢI NĂM</td>
                            <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="0" end="2">
                                <td class="Centre_BANGKQ_table_col_2" colspan="4"><span>${fifth}</span></td>    
                            </c:forTokens>

                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="3" end="5">
                                <td class="Centre_BANGKQ_table_col_2" colspan="4"><span>${fifth}</span></td>    
                            </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1">GIẢI SÁU</td>
                            <c:forTokens items="${lottery.sixth}" var="sixth" delims="-" >
                                <td class="Centre_BANGKQ_table_col_2" colspan="4"><span>${sixth}</span></td>    
                            </c:forTokens>
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td class="Centre_BANGKQ_table_col_1">GIẢI BẢY</td>
                            <c:forTokens items="${lottery.seventh}" var="seventh" delims="-" >
                                <td class="Centre_BANGKQ_table_col_2" colspan="3"><span>${seventh}</span></td>
                            </c:forTokens>                                        
                        </tr>
                    </table>
                </div>
            </div>                                             
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


