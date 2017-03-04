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
                                KẾT QUẢ XỔ SỐ MIỀN BẮC
                                <p><c:if test="${dayOfWeek!='cn'}">thứ ${dayOfWeek}, </c:if><c:if test="${dayOfWeek=='cn'}">chủ nhật, </c:if>ngày ${lottery.openDate}</p>
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
                                                    <td class="Centre_BANGKQ_table_col_2 do" style="font-weight: normal;">${lottery.special_xsmb}</td>
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
                                        <br/>&nbsp;
                                        <%@include file="include/cuphapkinhdoanh.jsp" %>
                                    </c:forEach>
                                    <div class="both"></div>		     
                                </div>
                            </div>
                        </div>
                        <c:if test="${statusLottery.index==0 ||statusLottery.index==1}">
                            <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                            <!-- ND_300x250_Mobile -->
                            <ins class="adsbygoogle"
                                 style="display:inline-block;width:300px;height:250px"
                                 data-ad-client="ca-pub-5650397705160395"
                                 data-ad-slot="7618587463"></ins>
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

