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
                                <c:if test="${statusLottery.index==0}">
                                    <h1>KẾT QUẢ XỔ SỐ MIỀN BẮC - <c:if test="${dayOfWeek!='cn'}">THỨ ${dayOfWeek}</c:if><c:if test="${dayOfWeek=='cn'}">CHỦ NHẬT</c:if>,<a href="/xsmb-xo-so-mien-bac-ngay-${fn:replace(lottery.openDate, '/', '-')}.html" class="linkngay">${lottery.openDate}</a>
                                        </h1>
                                </c:if>
                                <c:if test="${statusLottery.index!=0}">
                                    <h2>KẾT QUẢ XỔ SỐ MIỀN BẮC - <c:if test="${dayOfWeek!='cn'}">THỨ ${dayOfWeek}</c:if><c:if test="${dayOfWeek=='cn'}">CHỦ NHẬT</c:if>, ${lottery.openDate}</h2>
                                </c:if>
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
                                                    <td class="Centre_BANGKQ_table_col_2 do" colspan="12" style="font-weight: normal;font-size: 15px;">${lottery.special_xsmb}</td>
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
                        </c:forEach>
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

