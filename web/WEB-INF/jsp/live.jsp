<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="include/header.jsp" %>
        <script>
            //xosomb
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
                var url = "/ajax/live.htm?r=MB";
                //truc tiep mien bac
                if (currTime > 181000 && currTime < 184500 && region === 'MB') {
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
//                    console.log(sResponse);
                    document.getElementById(id).innerHTML = sResponse;
                });
            }
            countDownMB();
        </script>
    </head>
    <body>
        <center>
            <!-- top-->
            <%@include file="include/top.jsp" %>
            <div class="Main">
                <div class="Left">                    
                    <div class="Left_BANGKQ_title">
                        <h1>TRỰC TIẾP KẾT QUẢ XỔ SỐ MIỀN BẮC ${sDDMMYYYY}</h1>
                    </div>
                    <div class="TRUCTIEPKQXS_box" id="box_time_xsmb">
                        <span class="do">ĐANG CHỜ KQXS MIỀN BẮC LÚC 18H15 - ${sDDMMYYYY}</span>. CÒN:
                        <div class="TRUCTIEPKQXS_box_Clock">
                            <span class="TRUCTIEPKQXS_box_gio" id="mbhour">08</span>-<span class="TRUCTIEPKQXS_box_phut" id="mbminu">34</span>-<span class="TRUCTIEPKQXS_box_giay" id="mbsecond">59</span>
                        </div>
                        <p>
                            giờ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;phút&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;giây 
                        </p>
                    </div>
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
                    <div id="ketqua"></div>
                    <div id="ketquaduoi"></div>
                    <c:if test="${listLottery!=null}">
                        <c:forEach items="${listLottery}" var="lottery" begin="0" end="0" varStatus="statusLottery">
                            <div class="Left_BANGKQ_title">
                                <h2><a href="/xsmb-xo-so-mien-bac-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}</a></h2>
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
                                                    <td class="Centre_BANGKQ_table_col_2 do" colspan="12" style="font-weight: normal">${lottery.special_xsmb}</td>
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
                                <div class="Centre_BANGKQ_table_note">
                                    Lấy kết quả:<span class="do"> X1 MB</span> gửi <span class="do">8185</span><br />
                                    Số đẹp trong ngày:<span class="do"> C1 MB</span> gửi <span class="do">8585</span><br />
                                </div>
                            </div>
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
                        </c:forEach>               
                    </c:if>
                    <c:if test="${listCapso!=null}">
                        <div class="Left_THONGKE_title">
                            <h2>THỐNG KÊ NHANH KẾT QUẢ XỔ SỐ MIỀN BẮC</h2>
                        </div>
                        <div class="Left_THONGKE_bg">
                            <div class="Left_THONGKE_title_small">
                                <h3>
                                    <img src="images/xsmb-icon-list.gif" style="padding:0 5px 0 0"/>
                                    8 số LOTO Miền Bắc có khả năng về hôm nay
                                </h3>
                            </div>
                            <div class="Centre_TKCKdanLOTO_bg">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                    <tr class="Centre_TKCKdanLOTO_table_row_1">
                                        <td class="Centre_TKCKdanLOTO_table_col_1">Số</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_2">Ngày chưa về</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_3">Gan cực đại</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_4">Lần xuất hiện</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_5">Ngày về gần nhất</td>
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
                        <c:if test="${listCapsoCham!=null}">
                            <div class="Left_THONGKE_title_small">
                                <img src="images/xsmb-icon-list.gif" style="padding:0 5px 0 0"/>
                                Thống kê chạm đầu ĐẶC BIỆT Miền Bắc hôm nay
                            </div>
                            <div class="Centre_TKCKdanLOTO_bg">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                                    <tr class="Centre_TKCKdanLOTO_table_row_1">
                                        <td class="Centre_TKCKdanLOTO_table_col_1">Đầu</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_2">Ngày chưa về</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_3">Gan cực đại</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_4">Lần xuất hiện</td>
                                        <td class="Centre_TKCKdanLOTO_table_col_5">Ngày về gần nhất</td>
                                    </tr>
                                    <c:forEach items="${listCapsoCham}" var="capso">
                                        <tr class="Centre_TKCKdanLOTO_table_row_2">
                                            <td class="Centre_TKCKdanLOTO_table_col_1">
                                                <img src="images/${capso.capso}.png"/>
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
                    </div>
                    <c:if test="${listLottery!=null}">
                        <c:forEach items="${listLottery}" var="lottery" begin="1" end="1" varStatus="statusLottery">
                            <div class="Left_BANGKQ_title">
                                <h2><a href="/xsmb-xo-so-mien-bac-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}</a></h2>
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
                                                    <td class="Centre_BANGKQ_table_col_2 do" colspan="12" style="font-weight: normal">${lottery.special_xsmb}</td>
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
                                <div class="Centre_BANGKQ_table_note">
                                    Lấy kết quả:<span class="do"> X1 MB</span> gửi <span class="do">8185</span><br />
                                    Số đẹp trong ngày:<span class="do"> C1 MB</span> gửi <span class="do">8585</span><br />
                                </div>
                            </div>
                        </c:forEach>               
                    </c:if>
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

