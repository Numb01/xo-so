<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="include/header.jsp" %>
        <script type="text/javascript">
            function validation() {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                var name = $('#name').val();
                var email = $('#email').val();
                var title = $('#title').val();
                var content = $('#content').val();
                if (name !== null && email !== null && title !== null && content !== null) {
                    if (re.test(email)) {
                        return true;
                    }
                    return false;
                }
                } else {
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <center>
            <!-- top-->
            <%@include file="include/top.jsp" %>
            <div class="Main">
                <div class="Left">
                    <li style="text-align: left;font-size: 20px;">
                        Đại Lý Vé Số XSMB xin kính chào quý khách, chúng tôi là đơn vị quản lý,
                        <br/>&nbsp;&nbsp;&nbsp;hoạt động website tường thuật trực tiếp KQXS:<a href="http://xsmb.vn/" style="color: red"> http://xsmb.vn</a>
                    </li>
                    <br/>
                    <li style="text-align: left;font-size: 20px;">
                        Website Xổ Số Miền Bắc được đông đảo người dùng cùng với các anh chị
                        <br/>&nbsp;&nbsp;&nbsp;đại lý vé số bình chọn là website xổ số "nhanh nhất và Chính xác nhất"
                        <br/>&nbsp;&nbsp;&nbsp;hiện nay tại Việt Nam.    
                    </li>
                    <br/>
                    <div style="padding: 0 0 0 26px;margin: 0 0 48px">
                        <form method="post" name="frmlienhe" onsubmit="return validation();">  
                            <h3>Thông tin liên hệ</h3>
                            <br/>
                            <table border="0">
                                <tr>
                                    <td><label>Họ tên</label></td>
                                    <td><input type="text" size="50" name="name" id="name"/></td>
                                </tr>
                                <tr>
                                    <td><label>Email</label></td>
                                    <td><input type="text" size="50" name="email" id="email"/></td>
                                </tr>
                                <tr>
                                    <td><label>Tiêu đề</label></td>
                                    <td><input type="text" size="50" name="title" id="title"/></td>
                                </tr>
                                <tr>
                                    <td valign="bottom"><label>Nội dung</label></td>
                                    <td><textarea cols="47" rows="5" name="content" id="content"></textarea></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" class="btnVang" value="Gửi liên hệ"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
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

