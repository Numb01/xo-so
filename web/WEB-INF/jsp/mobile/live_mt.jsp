<%-- 
    Document   : index
    Created on : Mar 4, 2016, 1:58:26 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="vi">
    <head>
        <%@include file="include/header.jsp" %>
        <script>
            //xosomb
            var region = "MT";
            $(function () {
                loadLive();
                //loadKQ();
                var t = setInterval(function () {
                    loadLive();
                }, 5000);
            });

            function loadLive() {
                var currTime = getCurrHHMMSS();
                var url = "/ajax/mobile/live_mien_trung.htm?r=MT";
//                var urlDau = "/ajax/live_dau.htm?r=MT";
//                var urlDuoi = "/ajax/live_duoi.htm?r=MT";
                //truc tiep mien bac
                if (currTime > 171000 && currTime < 174500) {
                    loadAjax(url, 'ketqua');
                }

                if (currTime > 171500 || currTime === 171500) {
                    $('#box_time_xsmb').hide();
                    $('#tructiep').hide();
                }
            }

            function loadKQ() {
                var currTime = getCurrHHMMSS();
                var url = "/buffer/";
                if (currTime >= 174500) {
                    url = url + "xsmt.html";
                    loadAjax(url, 'ketqua');
                }
            }

            function loadAjax(url, id) {
                $.ajax({
                    url: url,
                    dataType: 'text',
                    cache: false
                }).done(function (sResponse) {
                    console.log(sResponse);
                    document.getElementById(id).innerHTML = sResponse;
                });
            }

            countDownMT();
        </script>
    </head>
    <body>
        <center>
            <!-- top-->
            <%@include file="include/top.jsp" %>
            <div class="Main">
                <div class="KQXSMB_box" id="box_time_xsmb">
                    <h1>
                        TRỰC TIẾP KẾT QUẢ XỔ SỐ MIỀN TRUNG
                        <span>ngày ${sDDMMYYYY}</span>
                    </h1>
                    <div class="TRUCTIEPKQXS_box">
                        <span class="do">ĐANG CHỜ KQXS MIỀN TRUNG LÚC 17H15 - ${sDDMMYYYY}</span>
                        <div class="TRUCTIEPKQXS_box_Clock">
                            <span class="TRUCTIEPKQXS_box_gio" id="mbhour">08</span>-<span class="TRUCTIEPKQXS_box_phut" id="mbminu">34</span>-<span class="TRUCTIEPKQXS_box_giay" id="mbsecond">59</span>
                        </div>
                    </div>
                </div>
                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                <!-- ND_300x250_Web -->
                <ins class="adsbygoogle"
                     style="display:inline-block;width:300px;height:250px"
                     data-ad-client="ca-pub-5650397705160395"
                     data-ad-slot="4944322664"></ins>
                <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
                </script>
                <div class="KQXSMB_box" id="ketqua"></div>
                <c:if test="${lotterys!=null}">
                    <div class="Left_BANGKQ_title">
                        <h2 class="h2_bg_1">
                            <a href="/xsmt-xo-so-mien-trung-ngay-${fn:replace(lotterys.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN TRUNG ${lotterys.openDate}</a>
                        </h2>
                    </div>
                    <div class="Left_background">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1 do">Tỉnh</td>
                                <c:forTokens items="${lotterys.province}" delims="+" var="province" varStatus="status">
                                    <td class="TableKQMN_col_3 TableKQMB_row_2" <c:if test="${lengthProvince==2}">style="width: 35%;"</c:if> <c:if test="${lengthProvince==3}">style="width: 25%;"</c:if>>
                                        <c:forTokens items="${lotterys.code}" var="code" delims="+" begin="${status.index}" end="${status.index}">
                                            <c:forTokens items="${lotterys.link}" delims="+" var="link" begin="${status.index}" end="${status.index}">
                                                <a href="${link}"><span class="do">${province}</span></a>
                                                </c:forTokens>
                                            </c:forTokens>
                                    </td>
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
                    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                    <!-- ND_300x250_Web -->
                    <ins class="adsbygoogle"
                         style="display:inline-block;width:300px;height:250px"
                         data-ad-client="ca-pub-5650397705160395"
                         data-ad-slot="4944322664"></ins>
                    <script>
                                (adsbygoogle = window.adsbygoogle || []).push({});
                    </script>
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
            </div>

            <%@include file="include/footer.jsp" %>

        </center>
    </body>
</html>

