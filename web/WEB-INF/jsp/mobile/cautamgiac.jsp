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
        <script type="text/javascript">
            $(function () {
                $('#datepicker').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
            });

            function changeCau() {
                var i = 1;
                var cau = $('#cau').val();

                if (eval(cau) > 9) {
                    cau = 9;
                    $('#cau').val(cau);
                }

                $('.CAULOTO_SONGAY').find('ul').find('li').each(function () {
                    $(this).removeAttr('id');
                });

                $('.CAULOTO_SONGAY').find('ul').find('li').each(function () {
                    if (i === eval(cau)) {
                        $(this).attr('id', 'CAULOTO_SONGAY_activer');
                    }
                    i++;
                });
            }

            function cauSubmit(c) {
                if (c !== null && c !== '') {
                    $('#cau').val(c);
                }
                $('#capso').val('');
                $('#frmCau').submit();
            }

            function caulo(cs) {
                $('#capso').val(cs);
                $('#cau').val('${cau}');
                $('#frmCau').submit();
            }
        </script>
    </head>
    <body>
        <center>
            <!-- top-->
            <%@include file="include/top.jsp" %>
            <div class="Main">     
                <div class="CAU_bg">
                    <div class="CAU_title">
                        CẦU LOTO TAM GIÁC
                    </div>
                    <div class="CAULOTO_text">
                        <c:if test="${resultcautamgiac!=null}">
                            <c:set var="max" value="0"/>    
                            <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                <c:if test="${max<phantichloto.vitri1}"><c:set var="max" value="${phantichloto.vitri1}"/></c:if>
                            </c:forEach>
                            CẦU LOTO THEO <strong class="do">THỨ ${todayOfWeek} - ${today}</strong>
                        </c:if>
                    </div>
                    <div class="CAU_box_form">
                        <form method="post" id="frmCau">
                            <input type="hidden" name="capso" id="capso"/>    
                            Biên ngày cầu chạy:
                            <input name="sdate" type="text" value="${sdate}" maxlength="6" class="boxLich" id="datepicker" style="cursor: pointer"/>
                            Số ngày cầu chạy:&nbsp; 
                            <input name="cau" type="text" value="${cau}" size="3" maxlength="20" class="boxFrom" id="cau" onchange="changeCau();" onpaste="return onPate(event)" onkeypress="return isNumber(event)"/><br />
                            Tỉnh:
                            <select class="From_Tinh" name="code" onchange="cauSubmit('2');">
                                <c:if test="${listCompany!=null}">
                                    <c:forEach items="${listCompany}" var="company">
                                        <option value="${company.code}" <c:if test="${code==company.code}">selected</c:if>>${company.company}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <select class="From_Giai" name="giai">
                                <c:if test="${code=='XSTD'}">
                                    <option value="00" <c:if test="${giai=='00'}">selected</c:if>>G00 - Giải đặc biệt</option>
                                    <option value="11" <c:if test="${giai=='11'}">selected</c:if>>G11 - Giải nhất</option>
                                    <option value="21" <c:if test="${giai=='21'}">selected</c:if>>G21 - Giải nhì thứ 1</option>
                                    <option value="22" <c:if test="${giai=='22'}">selected</c:if>>G22 - Giải nhì thứ 2</option>
                                    <option value="31" <c:if test="${giai=='31'}">selected</c:if>>G31 - Giải ba thứ 1</option>
                                    <option value="32" <c:if test="${giai=='32'}">selected</c:if>>G32 - Giải ba thứ 2</option>
                                    <option value="33" <c:if test="${giai=='33'}">selected</c:if>>G33 - Giải ba thứ 3</option>
                                    <option value="34" <c:if test="${giai=='34'}">selected</c:if>>G34 - Giải ba thứ 4</option>
                                    <option value="35" <c:if test="${giai=='35'}">selected</c:if>>G35 - Giải ba thứ 5</option>
                                    <option value="36" <c:if test="${giai=='36'}">selected</c:if>>G36 - Giải ba thứ 6</option>
                                    <option value="41" <c:if test="${giai=='41'}">selected</c:if>>G41 - Giải tư thứ 1</option>
                                    <option value="42" <c:if test="${giai=='42'}">selected</c:if>>G42 - Giải tư thứ 2</option>
                                    <option value="43" <c:if test="${giai=='43'}">selected</c:if>>G43 - Giải tư thứ 3</option>
                                    <option value="44" <c:if test="${giai=='44'}">selected</c:if>>G44 - Giải tư thứ 4</option>
                                    <option value="51" <c:if test="${giai=='51'}">selected</c:if>>G51 - Giải năm thứ 1</option>
                                    <option value="52" <c:if test="${giai=='52'}">selected</c:if>>G52 - Giải năm thứ 2</option>
                                    <option value="53" <c:if test="${giai=='53'}">selected</c:if>>G53 - Giải năm thứ 3</option>
                                    <option value="54" <c:if test="${giai=='54'}">selected</c:if>>G54 - Giải năm thứ 4</option>
                                    <option value="55" <c:if test="${giai=='55'}">selected</c:if>>G55 - Giải năm thứ 5</option>
                                    <option value="56" <c:if test="${giai=='56'}">selected</c:if>>G56 - Giải năm thứ 6</option>
                                    <option value="61" <c:if test="${giai=='61'}">selected</c:if>>G61 - Giải sáu thứ 1</option>
                                    <option value="62" <c:if test="${giai=='62'}">selected</c:if>>G62 - Giải sáu thứ 2</option>
                                    <option value="63" <c:if test="${giai=='63'}">selected</c:if>>G63 - Giải sáu thứ 3</option>
                                    <option value="71" <c:if test="${giai=='71'}">selected</c:if>>G71 - Giải bảy thứ 1</option>
                                    <option value="72" <c:if test="${giai=='72'}">selected</c:if>>G72 - Giải bảy thứ 2</option>
                                    <option value="73" <c:if test="${giai=='73'}">selected</c:if>>G73 - Giải bảy thứ 3</option>
                                    <option value="74" <c:if test="${giai=='74'}">selected</c:if>>G74 - Giải bảy thứ 4</option>
                                </c:if>
                                <c:if test="${code!='XSTD'}">
                                    <option value="00" <c:if test="${giai=='00'}">selected</c:if>>G00 - Giải đặc biệt</option>
                                    <option value="11" <c:if test="${giai=='11'}">selected</c:if>>G11 - Giải nhất</option>
                                    <option value="21" <c:if test="${giai=='21'}">selected</c:if>>G21 - Giải nhì thứ 1</option>
                                    <option value="31" <c:if test="${giai=='31'}">selected</c:if>>G31 - Giải ba thứ 1</option>
                                    <option value="32" <c:if test="${giai=='32'}">selected</c:if>>G32 - Giải ba thứ 2</option>                                    
                                    <option value="41" <c:if test="${giai=='41'}">selected</c:if>>G41 - Giải tư thứ 1</option>
                                    <option value="42" <c:if test="${giai=='42'}">selected</c:if>>G42 - Giải tư thứ 2</option>
                                    <option value="43" <c:if test="${giai=='43'}">selected</c:if>>G43 - Giải tư thứ 3</option>
                                    <option value="44" <c:if test="${giai=='44'}">selected</c:if>>G44 - Giải tư thứ 4</option>
                                    <option value="45" <c:if test="${giai=='44'}">selected</c:if>>G44 - Giải tư thứ 4</option>
                                    <option value="46" <c:if test="${giai=='44'}">selected</c:if>>G44 - Giải tư thứ 4</option>
                                    <option value="47" <c:if test="${giai=='44'}">selected</c:if>>G44 - Giải tư thứ 4</option>                                    
                                    <option value="51" <c:if test="${giai=='51'}">selected</c:if>>G51 - Giải năm thứ 1</option>                                    
                                    <option value="61" <c:if test="${giai=='61'}">selected</c:if>>G61 - Giải sáu thứ 1</option>
                                    <option value="62" <c:if test="${giai=='62'}">selected</c:if>>G62 - Giải sáu thứ 2</option>
                                    <option value="63" <c:if test="${giai=='63'}">selected</c:if>>G63 - Giải sáu thứ 3</option>
                                    <option value="71" <c:if test="${giai=='71'}">selected</c:if>>G71 - Giải bảy thứ 1</option>                                    
                                    <option value="81" <c:if test="${giai=='81'}">selected</c:if>>G81 - Giải tám thứ 1</option>
                                </c:if>    
                            </select>
                            <input type="button" class="Button_XEMKETQUA" style="margin:10px 0 0 0;cursor: pointer" value="XEM KẾT QUẢ" onclick="cauSubmit('');"/>                        
                        </form>
                        <p>Số ngày cầu chạy:</p>
                        <div class="CAULOTO_SONGAY">
                            <ul>                                    
                                <li <c:if test="${cau==1}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('1');">1</a></li>
                                <li <c:if test="${cau==2}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('2');">2</a></li>
                                <li <c:if test="${cau==3}">id="CAULOTO_SONGAY_activer"</c:if> ><a href="javascript:cauSubmit('3');">3</a></li>
                                <li <c:if test="${cau==4}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('4');">4</a></li>
                                <li <c:if test="${cau==5}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('5');">5</a></li>
                                <li <c:if test="${cau==6}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('6');">6</a></li>
                                <li <c:if test="${cau==7}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('7');">7</a></li>
                                <li <c:if test="${cau==8}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('8');">8</a></li>
                                <li <c:if test="${cau==9}">id="CAULOTO_SONGAY_activer"</c:if>><a href="javascript:cauSubmit('9');">9</a></li>
                                </ul>
                                <div class="both"></div>
                            </div>
                        </div>
                    <c:if test="${resultcautamgiac==null}">
                        <div class="CAULOTO_KQ_background">                            
                            <c:forEach var="i" begin="0" end="9">
                                <p>
                                    <strong>${i}:</strong>
                                    <c:forEach var="j" begin="0" end="9">
                                        <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>
                                    </c:forEach>
                                </p>
                            </c:forEach>
                        </div>
                    </c:if>  
                    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                    <!-- ND_Responsive_Mobile -->
                    <ins class="adsbygoogle"
                         style="display:block"
                         data-ad-client="ca-pub-5650397705160395"
                         data-ad-slot="9095320660"
                         data-ad-format="auto"></ins>
                    <script>
                                (adsbygoogle = window.adsbygoogle || []).push({});
                    </script>
                    <c:if test="${resultcautamgiac!=null}">                        
                        <div class="CAULOTO_KQ_background">
                            <p>Kết quả soi cầu ngày <strong class="red">${edate}</strong> có độ dài ${cau} <strong class="red"> ngày</strong></p>
                            <p>
                                <strong>0:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">                        
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '0')}">                            
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>                             
                                    </c:if>                        
                                </c:forEach>                            
                            </p>
                            <p>
                                <strong>1:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '1')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>                    
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>2:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '2')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>3:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '3')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>                            
                            </p>
                            <p>
                                <strong>4:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '4')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>5:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '5')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>6:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '6')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>7:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '7')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>8:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '8')}">
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>9:</strong>
                                <c:forEach items="${resultcautamgiac}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.caploto, '9')}">                                        
                                        <c:if test="${phantichloto.vitri1>-1}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer" onclick="caulo('${phantichloto.caploto}')">${phantichloto.caploto}</a></span>                    
                                            </c:if>
                                            <c:if test="${phantichloto.vitri1<0}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p><em>(Bạn có thể kích vào từng số LOTO để xem chi tiết)</em></p>
                        </div>                        
                        <c:if test="${capso!=null}">
                            <c:forEach items="${cautamgiac}" var="tamgiac" varStatus="status">
                                <div class="CAULOTO_KQ_ct">
                                    <c:if test="${status.index==0}">
                                        <p>Theo cầu này dự đoán ngày <strong>${ddmmyyyy}</strong> Loto sẽ về <strong class="red">${capso}</strong></p>
                                        </c:if>            	
                                    <center>
                                        <div class="PTLT_KQ_Table" style="text-align:center;">
                                            <div class="CAULOTO_new_TAMGIAC_box">                        
                                                <p><strong>Kết quả</strong>:
                                                    <c:forTokens items="${tamgiac.loto}" delims="," var="loto" varStatus="status">
                                                        <c:if test="${status.index<12}">${loto},</c:if>
                                                        <c:if test="${status.index==12}">${loto}<br/>,</c:if>
                                                        <c:if test="${status.index>12}">${loto},</c:if>
                                                    </c:forTokens>                                                    
                                                </p>
                                                <p>
                                                    <c:forTokens items="${tamgiac.cautamgiac}" delims="-" var="v">
                                                        <span class="CAULOTO_new_TAMGIAC_box_text">${v}</span><br />                    
                                                    </c:forTokens>                        
                                                </p>
                                            </div>
                                        </div>
                                    </center>
                                </div>
                            </c:forEach>
                        </c:if>     
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
            </div>

            <%@include file="include/footer.jsp" %>

        </center>
    </body>
</html>

