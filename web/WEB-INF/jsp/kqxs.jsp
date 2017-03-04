<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
                var url = "/buffer/";
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
                <div class="Left">   
                    <div id="ketqua"></div>
                    <c:if test="${listLottery!=null}">
                        <c:forEach items="${listLottery}" var="lottery"  varStatus="statusLottery">
                            <div class="Left_BANGKQ_title">
                                <a href="/xsmb-xo-so-mien-bac-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}</a>
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
                                                    <td class="Centre_BANGKQ_table_col_2 do" colspan="12">${lottery.special_xsmb}</td>
                                                </tr>
                                            </c:if>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td class="Centre_BANGKQ_table_col_1">GIẢI NHẤT</td>
                                                <td class="Centre_BANGKQ_table_col_2" colspan="12">${lottery.first}</td>
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1">GIẢI NHÌ</td>
                                                <c:forTokens items="${lottery.second}" var="second" delims="-">
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="6">${second}</td>
                                                </c:forTokens>                                        
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td rowspan="2" class="Centre_BANGKQ_table_col_1">GIẢI BA</td>
                                                <c:forTokens items="${lottery.third}" var="third" delims="-" begin="0" end="2">
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="4">${third}</td>
                                                </c:forTokens>                                        
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <c:forTokens items="${lottery.third}" var="third" delims="-" begin="3" end="5">
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="4">${third}</td>
                                                </c:forTokens>                                         
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1">GIẢI TƯ</td>
                                                <c:forTokens items="${lottery.fourth}" var="fourth" delims="-" >
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="3">${fourth}</td>
                                                </c:forTokens>                                        
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td rowspan="2" class="Centre_BANGKQ_table_col_1">GIẢI NĂM</td>
                                                <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="0" end="2">
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="4">${fifth}</td>    
                                                </c:forTokens>

                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="3" end="5">
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="4">${fifth}</td>    
                                                </c:forTokens>                                        
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg">
                                                <td class="Centre_BANGKQ_table_col_1">GIẢI SÁU</td>
                                                <c:forTokens items="${lottery.sixth}" var="sixth" delims="-" >
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="4">${sixth}</td>    
                                                </c:forTokens>                                        
                                            </tr>
                                            <tr class="Centre_BANGKQ_table_bg_1">
                                                <td class="Centre_BANGKQ_table_col_1">GIẢI BẢY</td>
                                                <c:forTokens items="${lottery.seventh}" var="seventh" delims="-" >
                                                    <td class="Centre_BANGKQ_table_col_2" colspan="3">${seventh}</td>
                                                </c:forTokens>                                        
                                            </tr>
                                        </table>
                                    </div>
                                </div>                                             
                                <c:forEach items="${listLotteryDauMB}" var="lotteryDauMB" begin="${statusLottery.index}" end="${statusLottery.index}">
                                    <div class="Left_BANGKQ_right">
                                        <div class="Centre_DAUDUOI_table">
                                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                                <tr class="Centre_DAUDUOI_table_row_1 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_1</c:if> ">
                                                        <td style="text-align:center;">Đầu</td>
                                                        <td style="text-align:left; padding:0 0 0 10px;">LOTO</td>
                                                    </tr>
                                                <c:forEach items="${lotteryDauMB}"  var="duoi" varStatus="status">
                                                    <tr class="Centre_DAUDUOI_table_row_2 <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_3</c:if> ">
                                                        <td class="Centre_DAUDUOI_table_col_1 do <c:if test="${lottery.special_xsmb!=null}">Centre_DAUDUOI_line_1</c:if> ">${status.index}</td>
                                                        <td class="Centre_DAUDUOI_table_col_2">${duoi}</td>
                                                    </tr>
                                                </c:forEach>                                    
                                            </table>
                                        </div>
                                    </div>
                                </c:forEach>            
                                <div class="both"></div>
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
                    <c:if test="${listLottery==null}">
                        <div class="Left_background">
                            Kết quả Xổ Số Miền Bắc ngày ${today} đang được update. Xin các bạn vui lòng chờ đợi
                        </div>
                    </c:if>
                    <c:if test="${lotterysMT!=null}">
                        <div class="Left_BANGKQ_title">
                            <h2><a href="/xsmt-xo-so-mien-trung-ngay-${fn:replace(lotterysMT.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN TRUNG ${lotterysMT.openDate}</a></h2>
                        </div>
                        <div class="Left_background">
                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1 do">Tỉnh</td>
                                    <c:forTokens items="${lotterysMT.province}" delims="+" var="province" varStatus="status">
                                        <td class="TableKQMN_col_3 TableKQMB_row_2" <c:if test="${lengthProvince==2}">style="width: 35%;"</c:if> <c:if test="${lengthProvince==3}">style="width: 25%;"</c:if>>
                                            <c:forTokens items="${lotterysMT.link}" delims="+" var="link" begin="${status.index}" end="${status.index}">
                                                <a href="${link}"><span class="do">${province}</span></a>
                                                </c:forTokens>
                                        </td>
                                    </c:forTokens>            
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI TÁM</td>
                                    <c:forTokens items="${lotterysMT.eighth}" delims="+" var="eighth">
                                        <td class="TableKQMN_col_2">${eighth}</td>
                                    </c:forTokens>     
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI BẢY</td>
                                    <c:forTokens items="${lotterysMT.seventh}" delims="+" var="seventh">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">${seventh}</td>
                                    </c:forTokens>     
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI SÁU</td>
                                    <c:forTokens items="${lotterysMT.sixth}" delims="+" var="sixthDelim">
                                        <td class="TableKQMN_col_2">
                                            <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                                                ${sixth}<br/>
                                            </c:forTokens>
                                        </td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI NĂM</td>
                                    <c:forTokens items="${lotterysMT.fifth}" var="fifth" delims="+">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">${fifth}</td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI TƯ</td>
                                    <c:forTokens items="${lotterysMT.fourth}" delims="+" var="fourthDelim">
                                        <td class="TableKQMN_col_2">
                                            <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                                                ${fourth}<br/>
                                            </c:forTokens>
                                        </td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI BA</td>
                                    <c:forTokens items="${lotterysMT.third}" delims="+" var="thirdDelim">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">
                                            <c:forTokens items="${thirdDelim}" delims="-" var="third">
                                                ${third}<br/>
                                            </c:forTokens>
                                        </td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI NHÌ</td>
                                    <c:forTokens items="${lotterysMT.second}" var="second" delims="+">
                                        <td class="TableKQMN_col_2">${second}</td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI NHẤT</td>
                                    <c:forTokens items="${lotterysMT.first}" var="fist" delims="+">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">${fist}</td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">ĐẶC BIỆT</td>
                                    <c:forTokens items="${lotterysMT.special}" var="special" delims="+">
                                        <td class="TableKQMN_col_2 do">${special}</td>
                                    </c:forTokens>
                                </tr>
                            </table>
                            <div class="both"></div>
                            <div class="Centre_BANGKQ_table_note">
                                Lấy kết quả:<span class="do"> X1 &lt;Mã tỉnh&gt;</span> gửi <span class="do">8185</span><br />
                                Số đẹp trong ngày:<span class="do"> C1 &lt;Mã tỉnh&gt;</span> gửi <span class="do">8585</span><br />
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
                            <h2><a href="/xsmt-xo-so-mien-nam-ngay-${fn:replace(lotterysMN.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN NAM ${lotterysMN.openDate}</a></h2>
                        </div>
                        <div class="Left_background">
                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1 do">Tỉnh</td>
                                    <c:forTokens items="${lotterysMN.province}" delims="+" var="province" varStatus="status">
                                        <td class="TableKQMN_col_3 TableKQMB_row_2" <c:if test="${lengthProvince==2}">style="width: 35%;"</c:if> <c:if test="${lengthProvince==3}">style="width: 25%;"</c:if>>
                                            <c:forTokens items="${lotterysMN.link}" delims="+" var="link" begin="${status.index}" end="${status.index}">
                                                <a href="${link}"><span class="do">${province}</span></a>
                                                </c:forTokens>
                                        </td>
                                    </c:forTokens>            
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI TÁM</td>
                                    <c:forTokens items="${lotterysMN.eighth}" delims="+" var="eighth">
                                        <td class="TableKQMN_col_2">${eighth}</td>
                                    </c:forTokens>     
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI BẢY</td>
                                    <c:forTokens items="${lotterysMN.seventh}" delims="+" var="seventh">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">${seventh}</td>
                                    </c:forTokens>     
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI SÁU</td>
                                    <c:forTokens items="${lotterysMN.sixth}" delims="+" var="sixthDelim">
                                        <td class="TableKQMN_col_2">
                                            <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                                                ${sixth}<br/>
                                            </c:forTokens>
                                        </td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI NĂM</td>
                                    <c:forTokens items="${lotterysMN.fifth}" var="fifth" delims="+">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">${fifth}</td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI TƯ</td>
                                    <c:forTokens items="${lotterysMN.fourth}" delims="+" var="fourthDelim">
                                        <td class="TableKQMN_col_2">
                                            <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                                                ${fourth}<br/>
                                            </c:forTokens>
                                        </td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI BA</td>
                                    <c:forTokens items="${lotterysMN.third}" delims="+" var="thirdDelim">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">
                                            <c:forTokens items="${thirdDelim}" delims="-" var="third">
                                                ${third}<br/>
                                            </c:forTokens>
                                        </td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI NHÌ</td>
                                    <c:forTokens items="${lotterysMN.second}" var="second" delims="+">
                                        <td class="TableKQMN_col_2">${second}</td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">GIẢI NHẤT</td>
                                    <c:forTokens items="${lotterysMN.first}" var="fist" delims="+">
                                        <td class="TableKQMN_col_2 TableKQMB_row_2">${fist}</td>
                                    </c:forTokens>
                                </tr>
                                <tr class="TableKQMB_row_1">
                                    <td class="TableKQMN_col_1">ĐẶC BIỆT</td>
                                    <c:forTokens items="${lotterysMN.special}" var="special" delims="+">
                                        <td class="TableKQMN_col_2 do">${special}</td>
                                    </c:forTokens>
                                </tr>
                            </table>
                            <div class="both"></div>
                            <div class="Centre_BANGKQ_table_note">
                                Lấy kết quả:<span class="do"> X1 &lt;Mã tỉnh&gt;</span> gửi <span class="do">8185</span><br />
                                Số đẹp trong ngày:<span class="do"> C1 &lt;Mã tỉnh&gt;</span> gửi <span class="do">8585</span><br />
                            </div>
                        </div>
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
            </div>
            <!-- right-->
            <%@include file="include/right.jsp" %>
            <div class="both"></div>
            </div>
            <!-- footer-->
            <%@include file="include/footer.jsp" %>

        </center>
    </body>
</html>

