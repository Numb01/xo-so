<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${region=='MB' && region!=null && listLottery!=null}">
    <c:forEach items="${listLottery}" var="lottery" begin="0" end="0" varStatus="statusLottery">
        <div class="Left_BANGKQ_title">
            KẾT QUẢ XỔ SỐ MIỀN BẮC NGÀY ${lottery.openDate}
        </div>
        <div class="Left_background_ajax">
            <div class="Left_BANGKQ_left_ajax">
                <div class="Centre_BANGKQ_bg">
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1_ajax do" <c:if test="${lottery.special_xsmb!=null}"> rowspan="2" </c:if> >ĐB</td>
                            <td class="Centre_BANGKQ_table_col_2_ajax do" colspan="12">${lottery.special}</td>
                        </tr>
                        <c:if test="${lottery.special_xsmb!=null}">
                            <tr class="Centre_BANGKQ_table_bg">
                                <td class="Centre_BANGKQ_table_col_2_ajax do" colspan="12"><span>${lottery.special_xsmb}</span></td>
                            </tr>
                        </c:if>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td class="Centre_BANGKQ_table_col_1_ajax">G1</td>
                            <td class="Centre_BANGKQ_table_col_2_ajax" colspan="12"><span>${lottery.first}</span></td>
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1_ajax">G2</td>
                            <c:forTokens items="${lottery.second}" var="second" delims="-">
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="6"><span>${second}</span></td>
                                    </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td rowspan="2" class="Centre_BANGKQ_table_col_1_ajax">G3</td>
                            <c:forTokens items="${lottery.third}" var="third" delims="-" begin="0" end="2">
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="4"><span>${third}</span></td>
                                    </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <c:forTokens items="${lottery.third}" var="third" delims="-" begin="3" end="5">
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="4"><span>${third}</span></td>
                                    </c:forTokens>                                         
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1_ajax">G4</td>
                            <c:forTokens items="${lottery.fourth}" var="fourth" delims="-" >
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="3"><span>${fourth}</span></td>
                                    </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td rowspan="2" class="Centre_BANGKQ_table_col_1_ajax">G5</td>
                            <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="0" end="2">
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="4"><span>${fifth}</span></td>    
                                    </c:forTokens>

                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <c:forTokens items="${lottery.fifth}" var="fifth" delims="-" begin="3" end="5">
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="4"><span>${fifth}</span></td>    
                                    </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg">
                            <td class="Centre_BANGKQ_table_col_1_ajax">G6</td>
                            <c:forTokens items="${lottery.sixth}" var="sixth" delims="-" >
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="4"><span>${sixth}</span></td>    
                                    </c:forTokens>                                        
                        </tr>
                        <tr class="Centre_BANGKQ_table_bg_1">
                            <td class="Centre_BANGKQ_table_col_1_ajax">G7</td>
                            <c:forTokens items="${lottery.seventh}" var="seventh" delims="-" >
                                <td class="Centre_BANGKQ_table_col_2_ajax" colspan="3"><span>${seventh}</span></td>
                                    </c:forTokens>                                        
                        </tr>
                    </table>
                </div>
            </div>                                             
        </div>
    </c:forEach>       
</c:if>
<c:if test="${lotterys!=null && region=='MT'}">
    <div class="Left_BANGKQ_title">
        KẾT QUẢ XỔ SỐ MIỀN TRUNG NGÀY ${lotterys.openDate}
    </div>
    <div class="Left_background">
        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1 do">Tỉnh</td>
                <c:forTokens items="${lotterys.province}" delims="+" var="province">
                    <td class="TableKQMN_col_3 TableKQMB_row_2"><span class="do">${province}</span></td>
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
</c:if>
<c:if test="${lotterys!=null &&region=='MN'}">
    <div class="Left_BANGKQ_title">
        KẾT QUẢ XỔ SỐ MIỀN NAM NGÀY ${lotterys.openDate}
    </div>
    <div class="Left_background">
        <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#d1d1d1">
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1 do">Tỉnh</td>
                <c:forTokens items="${lotterys.province}" delims="+" var="province">
                    <td class="TableKQMN_col_3 TableKQMB_row_2"><span class="do">${province}</span></td>
                    </c:forTokens>                                
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI TÁM</td>
                <c:forTokens items="${lotterys.eighth}" delims="+" var="eighth">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${eighth}</span></td>
                        </c:forTokens>     
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI BẢY</td>
                <c:forTokens items="${lotterys.seventh}" delims="+" var="seventh">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${seventh}</span></td>
                        </c:forTokens>     
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI SÁU</td>
                <c:forTokens items="${lotterys.sixth}" delims="+" var="sixthDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${sixthDelim}" delims="-" var="sixth">
                            <span>${sixth}</span>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI NĂM</td>
                <c:forTokens items="${lotterys.fifth}" var="fifth" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${fifth}</span></td>
                        </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI TƯ</td>
                <c:forTokens items="${lotterys.fourth}" delims="+" var="fourthDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${fourthDelim}" delims="-" var="fourth">
                            <span>${fourth}</span>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI BA</td>
                <c:forTokens items="${lotterys.third}" delims="+" var="thirdDelim">
                    <td class="TableKQMN_col_2 TableKQMB_row_2">
                        <c:forTokens items="${thirdDelim}" delims="-" var="third">
                            <span>${third}</span>
                        </c:forTokens>
                    </td>
                </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI NHÌ</td>
                <c:forTokens items="${lotterys.second}" var="second" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${second}</span></td>
                        </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">GIẢI NHẤT</td>
                <c:forTokens items="${lotterys.first}" var="fist" delims="+">
                    <td class="TableKQMN_col_2 TableKQMB_row_2"><span>${fist}</span></td>
                        </c:forTokens>
            </tr>
            <tr class="TableKQMB_row_1">
                <td class="TableKQMN_col_1">ĐẶC BIỆT</td>
                <c:forTokens items="${lotterys.special}" var="special" delims="+">
                    <td class="TableKQMN_col_2 do"><span>${special}</span></td>
                        </c:forTokens>
            </tr>
        </table>
    </div>
</c:if>


