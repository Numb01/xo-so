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
            var region = "MB";
            $(function () {
                loadLive();
                loadKQ();
                var t = setInterval(function () {
                    loadLive();
                    //loadKQ();
                }, 5000);
            });

            function loadLive() {
                var currTime = getCurrHHMMSS();
                console.log("currTime<<<<<<<<<>>>>>>>>>" + currTime);
                var url = "/ajax/live.htm?r=";
                //truc tiep mien bac
                if (currTime > 181000 && currTime < 184500 && region === 'MB') {
                    url = url + region;
                    loadAjax(url, 'ketqua');
                }

                if (currTime > 181500 || currTime === 181500) {
                    $('#box_time_xsmb').hide();
                }
            }

            function loadKQ() {
                var currTime = getCurrHHMMSS();
                var url = "/buffer/mobile/";
                if (currTime >= 184500 && region === 'MB') {
                    url = url + "xsmb.html";
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

            //countDownMB() ;
        </script>
    </head>
    <body>
        <center>
            <!-- top-->
            <%@include file="include/top.jsp" %>
            <div class="Main">
                <div class="KQXSMB_box" id="ketqua"></div>
                <c:if test="${listLottery!=null}">
                    <c:forEach items="${listLottery}" var="lottery" varStatus="statusLottery">
                        <div class="KQXSMB_box">
                            <div class="KQXSMB_title">
                                <a href="/xsmb-xo-so-mien-bac-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}</a>
                            </div>
                            <div class="Left_BANGKQ_left">
                                <div class="Centre_BANGKQ_bg">
                                    <div class="Centre_BANGKQ_bg_1">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#d1d1d1">
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1 do" <c:if test="${lottery.special_xsmb!=null}"> rowspan="2" </c:if>>ĐB</td>
                                                <td class="Centre_BANGKQ_table_col_2 do">${lottery.special}</td>
                                            </tr>
                                            <c:if test="${lottery.special_xsmb!=null}">
                                                <tr class="Centre_BANGKQ_table_bg">                                                
                                                    <td class="Centre_BANGKQ_table_col_2 do">${lottery.special_xsmb}</td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td class="Centre_BANGKQ_table_col_1 Centre_BANGKQ_table_bg_1">G1</td>
                                                <td class="Centre_BANGKQ_table_col_2 Centre_BANGKQ_table_bg_1">${lottery.first}</td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1">G2</td>
                                                <td class="Centre_BANGKQ_table_col_2">
                                                    <c:forTokens items="${lottery.second}" var="second" delims="-" varStatus="status">
                                                        <c:if test="${status.index==0}">${second}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index!=0}">${second}</c:if>
                                                    </c:forTokens>
                                                </td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td rowspan="2" class="Centre_BANGKQ_table_col_1">G3</td>
                                                <td rowspan="2" class="Centre_BANGKQ_table_col_2">
                                                    <c:forTokens items="${lottery.third}" var="third" delims="-" begin="0" end="2" varStatus="status">
                                                        <c:if test="${status.index<2}">${third}&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>1}">${third}</c:if>
                                                    </c:forTokens>
                                                    <br />
                                                    <c:forTokens items="${lottery.third}" var="third" delims="-" begin="3" end="5" varStatus="status">
                                                        <c:if test="${status.index<5}">${third}&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>4}">${third}</c:if>
                                                    </c:forTokens>
                                                </td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">

                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1">G4</td>
                                                <td class="Centre_BANGKQ_table_col_2">
                                                    <c:forTokens items="${lottery.fourth}" var="fourth" delims="-" varStatus="status" >
                                                        <c:if test="${status.index<4}">${fourth}&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>3}">${fourth}</c:if>
                                                    </c:forTokens>                                                
                                                </td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td rowspan="2" class="Centre_BANGKQ_table_col_1">G5</td>
                                                <td rowspan="2" class="Centre_BANGKQ_table_col_2">
                                                    <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="0" end="2" varStatus="status">
                                                        <c:if test="${status.index<2}">${fifth}&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>1}">${fifth}</c:if>
                                                    </c:forTokens>
                                                    <br />
                                                    <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="3" end="5" varStatus="status">
                                                        <c:if test="${status.index<5}">${fifth}&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>4}">${fifth}</c:if>
                                                    </c:forTokens>
                                                </td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1 Centre_BANGKQ_table_bg">G6</td>
                                                <td class="Centre_BANGKQ_table_col_2 Centre_BANGKQ_table_bg">
                                                    <c:forTokens items="${lottery.sixth}" var="sixth" delims="-" varStatus="status">
                                                        <c:if test="${status.index<2}">${sixth}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>1}">${sixth}</c:if>
                                                    </c:forTokens>                                                
                                                </td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td class="Centre_BANGKQ_table_col_1">G7</td>
                                                <td class="Centre_BANGKQ_table_col_2">
                                                    <c:forTokens items="${lottery.seventh}" var="seventh" delims="-" varStatus="status">
                                                        <c:if test="${status.index<3}">${seventh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                                        <c:if test="${status.index>2}">${seventh}</c:if>
                                                    </c:forTokens>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <c:forEach items="${listLotteryDauMB}" var="lotteryDauMB" begin="${statusLottery.index}" end="${statusLottery.index}">
                                        <div class="Centre_BANGKQ_bg_2">
                                            <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#d1d1d1">
                                                <c:forEach items="${lotteryDauMB}"  var="duoi" varStatus="status">
                                                    <c:if test="${status.index==0}">
                                                        <tr class="Centre_BANGKQ_table_bg">
                                                            <td class="Centre_BANGKQ_table_col_3 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_3</c:if> " style="text-align:center;"><strong class="do">${status.index}</strong></td>
                                                            <td class="Centre_BANGKQ_table_col_4 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_4</c:if>" style="text-align:left; padding:0 0 0px 5px;">${duoi}</td>
                                                            </tr>                                                            
                                                    </c:if>
                                                    <c:if test="${status.index!=0}">
                                                        <tr class="Centre_BANGKQ_table_bg">
                                                            <td class="Centre_BANGKQ_table_col_3 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_3</c:if>" ><strong class="do">${status.index}</strong></td>
                                                            <td class="Centre_BANGKQ_table_col_4 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_4</c:if>" >${duoi}</td>
                                                            </tr>    
                                                    </c:if>    
                                                </c:forEach>                                        
                                            </table>
                                        </div>
                                    </c:forEach>
                                    <div class="both"></div>		     
                                </div>
                            </div>
                            <%@include file="include/cuphapkinhdoanh.jsp" %>
                        </div>
                    </c:forEach>
                </c:if>
                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                <!-- ND_300x250_Web -->
                <ins class="adsbygoogle"
                     style="display:inline-block;width:300px;height:250px"
                     data-ad-client="ca-pub-5650397705160395"
                     data-ad-slot="4944322664"></ins>
                <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
                </script>
                <c:if test="${lotterysMT!=null}">
                    <div class="Left_BANGKQ_title">
                        <h2 class="h2_bg_1">
                            <a href="/xsmn-xo-so-mien-nam-ngay-${fn:replace(lotterysMT.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN TRUNG ${lotterysMT.openDate}</a>
                        </h2>
                    </div>
                    <div class="Left_background">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1 do">Tỉnh</td>
                                <c:forTokens items="${lotterysMT.province}" delims="+" var="province" varStatus="status">
                                    <td class="TableKQMN_col_3 TableKQMB_row_2" style="width: 25%;">
                                        <c:forTokens items="${lotterysMT.code}" var="code" delims="+" begin="${status.index}" end="${status.index}">
                                            <c:forTokens items="${lotterysMT.link}" delims="+" var="link" begin="${status.index}" end="${status.index}">
                                                <a href="${link}"><span class="do">${province}</span></a>
                                                </c:forTokens>
                                            </c:forTokens>
                                    </td>
                                </c:forTokens>                                
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G8</td>
                                <c:forTokens items="${lotterysMT.eighth}" delims="+" var="eighth">
                                    <td class="TableKQMN_col_2">${eighth}</td>
                                </c:forTokens>     
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G7</td>
                                <c:forTokens items="${lotterysMT.seventh}" delims="+" var="seventh">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">${seventh}</td>
                                </c:forTokens>     
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G6</td>
                                <c:forTokens items="${lotterysMT.sixth}" delims="+" var="sixthDelim">
                                    <td class="TableKQMN_col_2">
                                        <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                                            ${sixth}<br/>
                                        </c:forTokens>
                                    </td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G5</td>
                                <c:forTokens items="${lotterysMT.fifth}" var="fifth" delims="+">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">${fifth}</td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G4</td>
                                <c:forTokens items="${lotterysMT.fourth}" delims="+" var="fourthDelim">
                                    <td class="TableKQMN_col_2">
                                        <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                                            ${fourth}<br/>
                                        </c:forTokens>
                                    </td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G3</td>
                                <c:forTokens items="${lotterysMT.third}" delims="+" var="thirdDelim">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                                        <c:forTokens items="${thirdDelim}" delims="-" var="third">
                                            ${third}<br/>
                                        </c:forTokens>
                                    </td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G2</td>
                                <c:forTokens items="${lotterysMT.second}" var="second" delims="+">
                                    <td class="TableKQMN_col_2">${second}</td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G1</td>
                                <c:forTokens items="${lotterysMT.first}" var="fist" delims="+">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">${fist}</td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">ĐB</td>
                                <c:forTokens items="${lotterysMT.special}" var="special" delims="+">
                                    <td class="TableKQMN_col_2 do">${special}</td>
                                </c:forTokens>
                            </tr>
                        </table>
                    </div>
                    <div class="Left_background_2">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_DAUDUOI_col_1"><strong>ĐẦU</strong></td>
                                <c:forTokens items="${lotterysMT.province}" var="province" delims="+">
                                    <td class="TableKQMN_DAUDUOI_col_2"><strong>${province}</strong></td>
                                        </c:forTokens>
                            </tr>
                            <c:forEach items="${listDuoiMT}" var="duois" varStatus="status">
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
                <!-- Composite Start -->
                <div id="M192851ScriptRootC69408">
                    <div id="M192851PreloadC69408">
                        Loading...
                    </div>
                    <script>
                        (function () {
                            var D = new Date(), d = document, b = 'body', ce = 'createElement', ac = 'appendChild', st = 'style', ds = 'display', n = 'none', gi = 'getElementById';
                            var i = d[ce]('iframe');
                            i[st][ds] = n;
                            d[gi]("M192851ScriptRootC69408")[ac](i);
                            try {
                                var iw = i.contentWindow.document;
                                iw.open();
                                iw.writeln("<ht" + "ml><bo" + "dy></bo" + "dy></ht" + "ml>");
                                iw.close();
                                var c = iw[b];
                            }
                            catch (e) {
                                var iw = d;
                                var c = d[gi]("M192851ScriptRootC69408");
                            }
                            var dv = iw[ce]('div');
                            dv.id = "MG_ID";
                            dv[st][ds] = n;
                            dv.innerHTML = 69408;
                            c[ac](dv);
                            var s = iw[ce]('script');
                            s.async = 'async';
                            s.defer = 'defer';
                            s.charset = 'utf-8';
                            s.src = "//jsc.mgid.com/o/l/ole.vn.69408.js?t=" + D.getYear() + D.getMonth() + D.getDate() + D.getHours();
                            c[ac](s);
                        })();
                    </script>
                </div>
                <!-- Composite End -->
                <c:if test="${lotterysMN!=null}">
                    <div class="Left_BANGKQ_title">
                        <h2 class="h2_bg_1">
                            <a href="/xsmn-xo-so-mien-nam-ngay-${fn:replace(lotterysMN.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN NAM ${lotterysMN.openDate}</a>
                        </h2>
                    </div>
                    <div class="Left_background">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1 do">Tỉnh</td>
                                <c:forTokens items="${lotterysMN.province}" delims="+" var="province" varStatus="status">
                                    <td class="TableKQMN_col_3 TableKQMB_row_2" style="width: 25%;">
                                        <c:forTokens items="${lotterysMN.code}" var="code" delims="+" begin="${status.index}" end="${status.index}">
                                            <c:forTokens items="${lotterysMN.link}" delims="+" var="link" begin="${status.index}" end="${status.index}">
                                                <a href="${link}"><span class="do">${province}</span></a>
                                                </c:forTokens>
                                            </c:forTokens>
                                    </td>
                                </c:forTokens>                                
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G8</td>
                                <c:forTokens items="${lotterysMN.eighth}" delims="+" var="eighth">
                                    <td class="TableKQMN_col_2">${eighth}</td>
                                </c:forTokens>     
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G7</td>
                                <c:forTokens items="${lotterysMN.seventh}" delims="+" var="seventh">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">${seventh}</td>
                                </c:forTokens>     
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G6</td>
                                <c:forTokens items="${lotterysMN.sixth}" delims="+" var="sixthDelim">
                                    <td class="TableKQMN_col_2">
                                        <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                                            ${sixth}<br/>
                                        </c:forTokens>
                                    </td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G5</td>
                                <c:forTokens items="${lotterysMN.fifth}" var="fifth" delims="+">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">${fifth}</td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G4</td>
                                <c:forTokens items="${lotterysMN.fourth}" delims="+" var="fourthDelim">
                                    <td class="TableKQMN_col_2">
                                        <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                                            ${fourth}<br/>
                                        </c:forTokens>
                                    </td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G3</td>
                                <c:forTokens items="${lotterysMN.third}" delims="+" var="thirdDelim">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                                        <c:forTokens items="${thirdDelim}" delims="-" var="third">
                                            ${third}<br/>
                                        </c:forTokens>
                                    </td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G2</td>
                                <c:forTokens items="${lotterysMN.second}" var="second" delims="+">
                                    <td class="TableKQMN_col_2">${second}</td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">G1</td>
                                <c:forTokens items="${lotterysMN.first}" var="fist" delims="+">
                                    <td class="TableKQMN_col_2 TableKQMB_row_2">${fist}</td>
                                </c:forTokens>
                            </tr>
                            <tr class="TableKQMB_row_1">
                                <td class="TableKQMN_col_1">ĐB</td>
                                <c:forTokens items="${lotterysMN.special}" var="special" delims="+">
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
                                <c:forTokens items="${lotterysMN.province}" var="province" delims="+">
                                    <td class="TableKQMN_DAUDUOI_col_2"><strong>${province}</strong></td>
                                        </c:forTokens>
                            </tr>
                            <c:forEach items="${listDuoiMN}" var="duois" varStatus="status">
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

