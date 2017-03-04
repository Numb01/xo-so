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
                $('#datepicker1').datepicker({
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

            function caulo(cs, vt1, vt2) {
                $('#capso').val(cs);
                $('#cau').val('${cau}');
                $('#vt1').val(vt1);
                $('#vt2').val(vt2);
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
                        CẦU LOTO
                    </div>
                    <div class="CAULOTO_text">
                        <c:if test="${listPhanTichLoto!=null}">
                            <c:set var="max" value="0"/>    
                            <c:set var="total" value="0"/>
                            <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                <c:if test="${max<phantichloto.dodai}"><c:set var="max" value="${phantichloto.dodai}"/></c:if>
                                <c:if test="${phantichloto.dodai>=cau}">
                                    <c:set var="total" value="${total+1}"/>
                                </c:if>
                            </c:forEach>
                            TÌM ĐƯỢC ${total} CẦU CÓ ĐỘ DÀI <strong class="do">${cau} NGÀY</strong>
                        </c:if>
                    </div>
                    <div class="CAU_box_form">
                        <form method="post" id="frmCau">
                            <input type="hidden" name="capso" id="capso"/> 
                            <input type="hidden" name="vt1" id="vt1"/> 
                            <input type="hidden" name="vt2" id="vt2"/> 
                            Biên ngày cầu chạy:
                            <input name="edate" type="text" value="${edate}" maxlength="6" class="boxLich" id="datepicker1" style="cursor: pointer"/>
                            <br/>Số ngày cầu chạy:&nbsp; 
                            <input name="cau" type="text" value="${cau}" size="3" maxlength="20" class="boxFrom" id="cau" onchange="changeCau();" onpaste="return onPate(event)" onkeypress="return isNumber(event)"/><br />                            
                            Tỉnh:
                            <select class="From_Tinh" name="tinh">
                                <c:if test="${listCompany!=null}">
                                    <c:forEach items="${listCompany}" var="company">
                                        <option value="${company.code}" <c:if test="${tinh==company.code}">selected</c:if>>${company.company}</option>
                                    </c:forEach>
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
                        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                        <!-- ND_300x250_Web -->
                        <ins class="adsbygoogle"
                             style="display:inline-block;width:300px;height:250px"
                             data-ad-client="ca-pub-5650397705160395"
                             data-ad-slot="4944322664"></ins>
                        <script>
                                (adsbygoogle = window.adsbygoogle || []).push({});
                        </script>
                    <c:if test="${listPhanTichLoto!=null}">                        
                        <div class="CAULOTO_KQ_background">
                            <p>Kết quả soi cầu ngày <strong class="red">${edate}</strong> có độ dài ${cau} <strong class="red"> ngày</strong></p>
                            <p>
                                <strong>0:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">                        
                                    <c:if test="${fn:startsWith(phantichloto.capso, '0')}">                            
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>                             
                                    </c:if>                        
                                </c:forEach>                            
                            </p>
                            <p>
                                <strong>1:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '1')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>                    
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>2:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '2')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>3:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '3')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>                            
                            </p>
                            <p>
                                <strong>4:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '4')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>5:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '5')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>6:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '6')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>7:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '7')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>8:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '8')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p>
                                <strong>9:</strong>
                                <c:forEach items="${listPhanTichLoto}" var="phantichloto">
                                    <c:if test="${fn:startsWith(phantichloto.capso, '9')}">
                                        <c:if test="${phantichloto.dodai>=cau}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="cursor: pointer;color: red" onclick="caulo('${phantichloto.capso}', '${phantichloto.vitri1}', '${phantichloto.vitri2}')">${phantichloto.capso}</a></span>                    
                                            </c:if>
                                            <c:if test="${cau>phantichloto.dodai}">
                                            <span class="CAULOTO_KQ_box_number_xam"><a style="font-size: 51px">&nbsp;</a></span>                    
                                        </c:if>
                                    </c:if>                        
                                </c:forEach>

                            </p>
                            <p><em>(Bạn có thể kích vào từng số LOTO để xem chi tiết)</em></p>
                        </div>
                        <c:if test="${list2Lottery!=null}">                     
                            <c:forEach items="${list2Lottery}" var="listLottery">
                                <div class="CAULOTO_KQ_ct">                    
                                    <p>Theo cầu này dự đoán loto sẽ về <c:if test="${capso!=capdao}"> ${capso},${capdao}</c:if><c:if test="${capso==capdao}"> ${capso}</c:if>. Vị trí ghép cầu: Vị trí 1: ${vt1}, vị trí 2: ${vt2}</p>
                                    <p><strong>Cặp số </strong><strong class="red">${sCapSo.capso}</strong>: ${sCapSo.songaychuave} ngày chưa về. Gan cực đại: ${sCapSo.gancucdai}</p>
                                    <center>
                                        <c:if test="${tinh=='XSTD'}">
                                            <c:forEach items="${listLottery}" var="lottery"  >
                                                <div class="PTLT_KQ_Table">                                     
                                                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#dfdfdf">
                                                        <tr class="PTLT_KQ_Table_row_1">
                                                            <td colspan="13" align="center"><strong>Mở thưởng ngày ${lottery.openDate}</strong></td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Đặc biệt</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.special}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải nhất</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.first}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải nhì</td>
                                                            <c:forTokens items="${lottery.second}" delims="-" var="second">
                                                                <td colspan="6" align="center">${second}</td>
                                                            </c:forTokens>                                               
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1" rowspan="2">Giải ba</td>
                                                            <c:forTokens items="${lottery.third}" delims="-" var="third" begin="0" end="2">
                                                                <td colspan="4" align="center">${third}</td>
                                                            </c:forTokens>                        
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <c:forTokens items="${lottery.third}" delims="-" var="third" begin="3" end="5">
                                                                <td colspan="4" align="center">${third}</td>
                                                            </c:forTokens>                        
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải tư</td>
                                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth">
                                                                <td colspan="3" align="center">${fourth}</td>
                                                            </c:forTokens>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td rowspan="2" class="PTLT_KQ_Table_col_1">Giải năm</td>
                                                            <c:forTokens items="${lottery.fifth}" delims="-" var="fifth" begin="0" end="2">
                                                                <td colspan="4" align="center">${fifth}</td>
                                                            </c:forTokens>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <c:forTokens items="${lottery.fifth}" delims="-" var="fifth" begin="3" end="5">
                                                                <td colspan="4" align="center">${fifth}</td>
                                                            </c:forTokens>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải sáu</td>
                                                            <c:forTokens items="${lottery.sixth}" delims="-" var="sixth">
                                                                <td colspan="4" align="center">${sixth}</td>
                                                            </c:forTokens>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải bảy</td>
                                                            <c:forTokens items="${lottery.seventh}" delims="-" var="seventh">
                                                                <td colspan="3" align="center">${seventh}</td>
                                                            </c:forTokens>
                                                        </tr>
                                                    </table>
                                                </div>    
                                            </c:forEach>    
                                        </c:if>
                                        <c:if test="${tinh!='XSTD'}">
                                            <c:forEach items="${listLottery}" var="lottery"  >
                                                <div class="PTLT_KQ_Table">    
                                                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#dfdfdf">
                                                        <tr class="PTLT_KQ_Table_row_1">
                                                            <td colspan="13" align="center"><strong>Mở thưởng ngày ${lottery.openDate}</strong></td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Đặc biệt</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.special}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải nhất</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.first}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải nhì</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.second}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td rowspan="1" class="PTLT_KQ_Table_col_1">Giải ba</td>
                                                            <c:forTokens items="${lottery.third}" delims="-" var="third">
                                                                <td class="PTLT_KQ_Table_col_2" colspan="6">${third}</td>
                                                            </c:forTokens>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td rowspan="2" class="PTLT_KQ_Table_col_1">Giải tư</td>
                                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="0" end="2">
                                                                <td class="PTLT_KQ_Table_col_2" colspan="4">${fourth}</td>
                                                            </c:forTokens>                                
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="3" end="5">
                                                                <td class="PTLT_KQ_Table_col_2" colspan="4">${fourth}</td>
                                                            </c:forTokens>                                
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải năm</td>                                
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.fifth}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải sáu</td>
                                                            <c:forTokens items="${lottery.sixth}" delims="-" var="sixth">
                                                                <td class="PTLT_KQ_Table_col_2" colspan="4">${sixth}</td>
                                                            </c:forTokens>                                
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải bảy</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.seventh}</td>
                                                        </tr>
                                                        <tr class="PTLT_KQ_Table_row_2">
                                                            <td class="PTLT_KQ_Table_col_1">Giải tám</td>
                                                            <td class="PTLT_KQ_Table_col_2" colspan="12">${lottery.eighth}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:forEach>
                                        </c:if>
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

