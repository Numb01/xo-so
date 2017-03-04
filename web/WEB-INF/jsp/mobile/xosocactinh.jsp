<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="vi">
    <head>
        <%@include file="include/header.jsp" %>
    </head>
    <body>
        <center>
            <!-- top-->
            <%@include file="include/top.jsp" %>
            <div class="Main">     
                <c:if test="${listLottery!=null}">
                    <c:forEach items="${listLottery}" var="lottery" begin="0" end="0">
                        <div class="Left_BANGKQ_title">
                            <a href="/xs${code}-xo-so-${link2}-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">
                                KẾT QUẢ XỔ SỐ ${fn:toUpperCase(company)} - ${lottery.openDate}
                            </a>
                        </div>
                        <div class="Left_background">
                            <div class="Left_BANGKQ_left">
                                <div class="Centre_BANGKQ_bg">
                                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1 do">GIẢI TÁM</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.eighth}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI BẢY</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.seventh}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI SÁU</td>
                                            <c:forTokens items="${lottery.sixth}" var="sixth" delims="-">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="4">${sixth}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI NĂM</td>
                                            <c:forTokens items="${lottery.fifth}" var="fifth" delims="-">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="12">${fifth}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td rowspan="2" class="Centre_BANGKQ_table_col_1">GIẢI TƯ</td>
                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="0" end="2">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="4">${fourth}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth2" begin="3" end="6">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="3">${fourth2}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI BA</td>
                                            <c:forTokens items="${lottery.third}" delims="-" var="third">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="6">${third}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI NHÌ</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.second}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI NHẤT</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.first}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1 do">ĐẶC BIỆT</td>
                                            <td class="Centre_BANGKQ_table_col_2 do" colspan="12">${lottery.special}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <c:if test="${listDuoi!=null}">
                                <c:forEach items="${listDuoi}" var="listDuoi" begin="0" end="0">
                                    <div class="Left_BANGKQ_right">
                                        <div class="Centre_DAUDUOI_table">
                                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                                <tr class="Centre_DAUDUOI_table_row_1">
                                                    <td style="text-align:center;">Đầu</td>
                                                    <td style="text-align:left; padding:0 0 0 10px;">LOTO</td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">0</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[0]}" delims=" " var="dau0" varStatus="status">
                                                            ${dau0 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">1</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[1]}" delims=" " var="dau1" varStatus="status">
                                                            ${dau1 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">2</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[2]}" delims=" " var="dau2" varStatus="status">
                                                            ${dau2 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">3</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[3]}" delims=" " var="dau3" varStatus="status">
                                                            ${dau3 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">4</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[4]}" delims=" " var="dau4" varStatus="status">
                                                            ${dau4 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">5</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[5]}" delims=" " var="dau5" varStatus="status">
                                                            ${dau5 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">6</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[6]}" delims=" " var="dau6" varStatus="status">
                                                            ${dau6 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">7</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[7]}" delims=" " var="dau7" varStatus="status">
                                                            ${dau7 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">8</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[8]}" delims=" " var="dau8" varStatus="status">
                                                            ${dau8 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">9</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[9]}" delims=" " var="dau9" varStatus="status">
                                                            ${dau9 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div> 
                                </c:forEach>
                            </c:if>
                            <div class="both"></div>
                            <div class="Centre_BANGKQ_table_note">
                                Lấy kết quả:<span class="do"> X1 ${fn:toUpperCase(code)}</span> gửi <span class="do">8185</span><br />
                                Số đẹp trong ngày:<span class="do"> C1 ${fn:toUpperCase(code)}</span> gửi <span class="do">8585</span><br />
                            </div>
                        </div> 
                    </c:forEach> 
                    <c:forEach items="${listLottery}" var="lottery" begin="1" varStatus="statusaAdc">
                        <div class="Left_BANGKQ_title">
                            <a href="/xs${code}-xo-so-${link2}-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">
                                KẾT QUẢ XỔ SỐ ${fn:toUpperCase(company)} - ${lottery.openDate}
                            </a>
                        </div>
                        <div class="Left_background">
                            <div class="Left_BANGKQ_left">
                                <div class="Centre_BANGKQ_bg">
                                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1 do">GIẢI TÁM</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.eighth}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI BẢY</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.seventh}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI SÁU</td>
                                            <c:forTokens items="${lottery.sixth}" var="sixth" delims="-">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="4">${sixth}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI NĂM</td>
                                            <c:forTokens items="${lottery.fifth}" var="fifth" delims="-">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="12">${fifth}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td rowspan="2" class="Centre_BANGKQ_table_col_1">GIẢI TƯ</td>
                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="0" end="2">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="4">${fourth}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth2" begin="3" end="6">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="3">${fourth2}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI BA</td>
                                            <c:forTokens items="${lottery.third}" delims="-" var="third">
                                                <td class="Centre_BANGKQ_table_col_2" colspan="6">${third}</td>
                                            </c:forTokens>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI NHÌ</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.second}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg_1">
                                            <td class="Centre_BANGKQ_table_col_1">GIẢI NHẤT</td>
                                            <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.first}</td>
                                        </tr>
                                        <tr class="Centre_BANGKQ_table_bg">
                                            <td class="Centre_BANGKQ_table_col_1 do">ĐẶC BIỆT</td>
                                            <td class="Centre_BANGKQ_table_col_2 do" colspan="12">${lottery.special}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <c:if test="${listDuoi!=null}">
                                <c:forEach items="${listDuoi}" var="listDuoi" begin="0" end="0">
                                    <div class="Left_BANGKQ_right">
                                        <div class="Centre_DAUDUOI_table">
                                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                                <tr class="Centre_DAUDUOI_table_row_1">
                                                    <td style="text-align:center;">Đầu</td>
                                                    <td style="text-align:left; padding:0 0 0 10px;">LOTO</td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">0</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[0]}" delims=" " var="dau0" varStatus="status">
                                                            ${dau0 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">1</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[1]}" delims=" " var="dau1" varStatus="status">
                                                            ${dau1 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">2</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[2]}" delims=" " var="dau2" varStatus="status">
                                                            ${dau2 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">3</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[3]}" delims=" " var="dau3" varStatus="status">
                                                            ${dau3 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">4</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[4]}" delims=" " var="dau4" varStatus="status">
                                                            ${dau4 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">5</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[5]}" delims=" " var="dau5" varStatus="status">
                                                            ${dau5 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">6</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[6]}" delims=" " var="dau6" varStatus="status">
                                                            ${dau6 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">7</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[7]}" delims=" " var="dau7" varStatus="status">
                                                            ${dau7 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">8</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[8]}" delims=" " var="dau8" varStatus="status">
                                                            ${dau8 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                                <tr class="Centre_DAUDUOI_table_row_2">
                                                    <td class="Centre_DAUDUOI_table_col_1 do">9</td>
                                                    <td class="Centre_DAUDUOI_table_col_2">
                                                        <c:forTokens items="${listDuoi[9]}" delims=" " var="dau9" varStatus="status">
                                                            ${dau9 } 
                                                        </c:forTokens>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div> 
                                </c:forEach>
                            </c:if>
                            <div class="both"></div>
                            <div class="Centre_BANGKQ_table_note">
                                Lấy kết quả:<span class="do"> X1 ${fn:toUpperCase(code)}</span> gửi <span class="do">8185</span><br />
                                Số đẹp trong ngày:<span class="do"> C1 ${fn:toUpperCase(code)}</span> gửi <span class="do">8585</span><br />
                            </div>
                        </div>
                        <c:if test="${statusaAdc.index==8}">
                            <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                            <!-- LVS300x250 -->
                            <ins class="adsbygoogle"
                                 style="display:inline-block;width:300px;height:250px"
                                 data-ad-client="ca-pub-2360743497940079"
                                 data-ad-slot="5610345144"></ins>
                            <script>
                                (adsbygoogle = window.adsbygoogle || []).push({});
                            </script>
                        </c:if>
                    </c:forEach>  
                </c:if>  
            </div>

            <%@include file="include/footer.jsp" %>

        </center>
    </body>
</html>

