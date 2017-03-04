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
//                loadKQ();
                var t = setInterval(function () {
                    loadLive();
                    //loadKQ();
                }, 5000);
            });

            function loadLive() {
                var currTime = getCurrHHMMSS();
                var urlDau = "/ajax/mobile/live_dau.htm?r=MB";
                var urlDuoi = "/ajax/mobile/live_duoi.htm?r=MB";
                if (currTime > 181000 && currTime < 184500 && region === 'MB') {
//                    url = url + region;
                    loadAjax(urlDau, 'ketqua');
                    loadAjax(urlDuoi, 'ketquaduoi');
                    loadQclive();
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
            function loadQclive() {
                $("#qc").show();
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
                <div class="KQXSMB_box" id="ketquaduoi"></div>
                <div id="qc" style="display: none !important;">
                    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                    <!-- ND_336x280 -->
                    <ins class="adsbygoogle"
                         style="display:inline-block;width:336px;height:280px"
                         data-ad-client="ca-pub-5650397705160395"
                         data-ad-slot="8070755861"></ins>
                    <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
                    </script>
                </div>
                <c:if test="${listBangDacBiet!=null}">
                    <div class="KQXSMB_box">
                        <h2 class="h2_bg_1">
                            BẢNG THỐNG KÊ GIẢI ĐẶC BIỆT XSMB                          
                        </h2>
                        <div class="DACBIET_TK_BANGDB_KQ">
                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#dfdfdf">
                                <tr class="DACBIET_TK_BANGDB_KQ_row_1">
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>Thứ 2</h3></td>
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>Thứ 3</h3></td>
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>Thứ 4</h3></td>
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>Thứ 5</h3></td>
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>Thứ 6</h3></td>
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>Thứ 7</h3></td>
                                    <td class="DACBIET_TK_BANGDB_KQ_col"><h3>C.nhật</h3></td>
                                </tr>
                                <c:forEach items="${listBangDacBiet}" var="capso" varStatus="status">
                                    <c:if test="${capso.dayOfWeek!='cn'}"><fmt:parseNumber var="t" type="number" value="${capso.dayOfWeek}" /></c:if>
                                    <c:if test="${capso.dayOfWeek=='cn'}"><fmt:parseNumber var="t" type="number" value="8" /></c:if>
                                    <c:if test="${status.index > 0}"><fmt:parseNumber var="t" type="number" value="0" /></c:if>
                                    <c:if test="${status.index==0||capso.dayOfWeek=='2'}">
                                        <tr class="DACBIET_TK_BANGDB_KQ_row_2">
                                        </c:if>
                                        <c:if test="${capso.dayOfWeek=='2'||t>2}">
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">
                                                <c:if test="${capso.dayOfWeek=='2'&&capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>
                                                <c:if test="${capso.dayOfWeek!='2'||capso.capso==''}">
                                                    <p><span class="do">&nbsp;</span></p>
                                                </c:if>
                                            </td>
                                        </c:if>    

                                        <c:if test="${capso.dayOfWeek=='3'||t>3}">
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">
                                                <c:if test="${capso.dayOfWeek=='3'&&capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>
                                                <c:if test="${capso.dayOfWeek!='3'||capso.capso==''}">
                                                    <%--<span class="DACBIET_TK_BANGDB_KQ_col_time">&nbsp;</span>--%>
                                                    <p><span class="do">&nbsp;</span></p>
                                                </c:if>
                                            </td>
                                        </c:if>

                                        <c:if test="${capso.dayOfWeek=='4'||t>4}">
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">
                                                <c:if test="${capso.dayOfWeek=='4'&&capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>
                                                <c:if test="${capso.dayOfWeek!='4'||capso.capso==''}">
                                                    <p><span class="do">&nbsp;</span></p>
                                                </c:if>
                                            </td>
                                        </c:if>

                                        <c:if test="${capso.dayOfWeek=='5'||t>5}">
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">
                                                <c:if test="${capso.dayOfWeek=='5'&&capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>
                                                <c:if test="${capso.dayOfWeek!='5'||capso.capso==''}">
                                                    <p><span class="do">&nbsp;</span></p>
                                                </c:if>
                                            </td>
                                        </c:if>


                                        <c:if test="${capso.dayOfWeek=='6'||t>6}">
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">
                                                <c:if test="${capso.dayOfWeek=='6'&&capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>
                                                <c:if test="${capso.dayOfWeek!='6'||capso.capso==''}">                                                    
                                                    <p><span class="do">&nbsp;</span></p>
                                                </c:if>
                                            </td>
                                        </c:if>


                                        <c:if test="${capso.dayOfWeek=='7'||t>7}">
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">
                                                <c:if test="${capso.dayOfWeek=='7'&&capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>

                                            </td>
                                        </c:if>                                                                            

                                        <c:if test="${capso.dayOfWeek=='cn'}">                                        
                                            <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">     
                                                <c:if test="${capso.capso!=''}">
                                                    <p>${fn:replace(capso.special, 'red', 'do')}</p>
                                                </c:if>
                                                <c:if test="${capso.capso==''}">                                                    
                                                    <p><span class="do">&nbsp;</span></p>
                                                </c:if>
                                            </td>

                                        </c:if>    
                                        <c:if test="${capso.dayOfWeek=='cn'}">    
                                        </tr>
                                    </c:if>
                                    <c:if test="${capso.dayOfWeek!='cn'}"><fmt:parseNumber var="t" type="number" value="${capso.dayOfWeek}" /></c:if>
                                    <c:if test="${capso.dayOfWeek=='cn'}"><fmt:parseNumber var="t" type="number" value="8" /></c:if>
                                </c:forEach>                                    
                                <c:if test="${t<8}">
                                    <c:forEach begin="${t+1}" end="8">
                                        <td class="DACBIET_TK_BANGDB_KQ_col DACBIET_KQ_bg_tatca">                                            
                                            <p><span class="do">&nbsp;</span></p>                                            
                                        </td>
                                    </c:forEach>  
                                    </tr>    
                                </c:if>
                            </table>
                        </div>
                    </div>
                    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                    <!-- ND_336x280 -->
                    <ins class="adsbygoogle"
                         style="display:inline-block;width:336px;height:280px"
                         data-ad-client="ca-pub-5650397705160395"
                         data-ad-slot="8070755861"></ins>
                    <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
                    </script>
                </c:if>
                <c:if test="${listLottery!=null}">
                    <c:forEach items="${listLottery}" var="lottery" varStatus="statusLottery">
                        <div class="KQXSMB_box" id="ketqua">
                            <h2 class="h2_bg_2">
                                <a href="/xsmb-xo-so-mien-bac-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}</a>
                            </h2>
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
                            <div class="Centre_BANGKQ_table_note">
                                Lấy kết quả:<span class="do"> X1 MB</span> gửi <span class="do">8185</span><br />
                                Số đẹp trong ngày:<span class="do"> C1 MB</span> gửi <span class="do">8585</span><br />
                            </div>
                        </div>
                        <c:if test="${statusLottery.index==0 ||statusLottery.index==1}">
                            <div>
                                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                                <!-- ND_300x250_Web -->
                                <ins class="adsbygoogle"
                                     style="display:inline-block;width:300px;height:250px"
                                     data-ad-client="ca-pub-5650397705160395"
                                     data-ad-slot="4944322664"></ins>
                                <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
                                </script>
                            </div>
                        </c:if>
                        <c:if test="${statusLottery.index==2}">
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
                        </c:if>
                    </c:forEach>
                </c:if>
                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                <!-- ND_336x280 -->
                <ins class="adsbygoogle"
                     style="display:inline-block;width:336px;height:280px"
                     data-ad-client="ca-pub-5650397705160395"
                     data-ad-slot="8070755861"></ins>
                <script>
                                (adsbygoogle = window.adsbygoogle || []).push({});
                </script>
                <c:if test="${listCapso!=null}">
                    <div class="Left_THONGKE_title">
                        <h2 class="h2_bg_2">
                            THỐNG KÊ NHANH KẾT QUẢ XỔ SỐ MIỀN BẮC                            
                        </h2>
                    </div>
                    <div class="Left_THONGKE_bg">
                        <div class="Left_THONGKE_title_small">
                            <h3 class="h3_color_text_2">
                                <img src="images/xsmb-icon-list.gif" style="padding:0 5px 0 0"/>
                                8 số loto <span class="do">GAN</span> MIỀN BẮC có khả năng về hôm nay
                            </h3>
                        </div>
                        <div class="Centre_TKCKdanLOTO_bg">
                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                <tr class="Centre_TKCKdanLOTO_table_row_1">
                                    <td class="Centre_TKCKdanLOTO_table_col_1">Số</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_2">Ngày<br /> chưa về</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_3">Gan<br /> cực đại</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_4">Lần<br /> xuất hiện</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_5">Ngày về<br /> gần nhất</td>
                                </tr>
                                <c:forEach items="${listCapso}" var="capso" begin="0" end="7">
                                    <tr class="Centre_TKCKdanLOTO_table_row_2">
                                        <td class="Centre_TKCKdanLOTO_table_col_1">
                                            <span class="Centre_TKCKdanLOTO_box_number_LOTO">${capso.capso}</span>	
                                        </td>
                                        <td class="Centre_TKCKdanLOTO_table_col_2">${capso.songaychuave}</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_3">${capso.gancucdai}</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_4">${capso.solanxuathien}</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_5"><c:if test="${capso.ddmmyyyy!='no'}">${capso.ddmmyyyy}</c:if></td>
                                        </tr>                                
                                </c:forEach>                            
                            </table>
                        </div>
                    </c:if>   
                    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                    <!-- ND_336x280 -->
                    <ins class="adsbygoogle"
                         style="display:inline-block;width:336px;height:280px"
                         data-ad-client="ca-pub-5650397705160395"
                         data-ad-slot="8070755861"></ins>
                    <script>
                                (adsbygoogle = window.adsbygoogle || []).push({});
                    </script>
                    <div style="text-align: center">
                        <a href="http://soicaulo.com/" target="_blank"><img src="../../../images/soicaulo_300x100.gif" alt=""/></a>
                    </div>
                    <c:if test="${listCapsoCham!=null}">
                        <div class="Left_THONGKE_title_small">
                            <h3 class="h3_color_text_2">
                                <img src="images/xsmb-icon-list.gif" style="padding:0 5px 0 0"/>
                                Thống kê chạm đầu ĐẶC BIỆT MIỀN BẮC hôm nay
                            </h3>
                        </div>
                        <div class="Centre_TKCKdanLOTO_bg">
                            <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                <tr class="Centre_TKCKdanLOTO_table_row_1">
                                    <td class="Centre_TKCKdanLOTO_table_col_1">Đầu</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_2">Ngày<br /> chưa về</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_3">Gan<br /> cực đại</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_4">Lần<br /> xuất hiện</td>
                                    <td class="Centre_TKCKdanLOTO_table_col_5">Ngày về<br /> gần nhất</td>
                                </tr>
                                <c:forEach items="${listCapsoCham}" var="capso">
                                    <tr class="Centre_TKCKdanLOTO_table_row_2">
                                        <td class="Centre_TKCKdanLOTO_table_col_1">
                                            <img src="images/${capso.capso}.png" width="20px" height="20px"/>
                                        </td>
                                        <td class="Centre_TKCKdanLOTO_table_col_2">${capso.songaychuave}</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_3">${capso.gancucdai}</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_4">${capso.solanxuathien}</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_5"><c:if test="${capso.ddmmyyyy!='no'}">${capso.ddmmyyyy}</c:if></td>
                                        </tr>    
                                </c:forEach>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
            <%@include file="include/footer.jsp"%>
        </center>
    </body>
</html>

