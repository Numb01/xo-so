<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <!-- ND_728x90 -->
    <ins class="adsbygoogle"
         style="display:inline-block;width:728px;height:90px"
         data-ad-client="ca-pub-5650397705160395"
         data-ad-slot="3467589468"></ins>
    <script>
        (adsbygoogle = window.adsbygoogle || []).push({});
    </script>
</div>
<div style="clear: both;"></div>
<div class="Menu_bg">
    <div class="Menu">
        <ul>
            <li>
                <a href="http://sxmb.vn/">Trang chủ</a>
            </li>
            <li>
                <a href="http://xoso.wap.vn/xsmb-ket-qua-xo-so-mien-bac-xstd.html" target="_blank" title="xổ sô miền bắc">xo so mien bac</a>
            </li>
            <li>
                <a href="http://xoso.wap.vn/ket-qua-xo-so-mien-nam-xsmn.html" target="_blank" title="xổ số miền nam">xo so mien nam</a>
            </li>
            <li>
                <a href="http://sxmb.vn/xsmt-xo-so-mien-trung.html" target="_blank" title="xổ số miền trung">xo so mien trung</a>
            </li>
            <li>
                <a href="http://xemtuoi.com.vn/tu-vi-2017.html" target="_blank" title="xem tử vi 2017">xem tu vi</a>
            </li>
        </ul>
        <div class="both"></div>
    </div>
</div>
<div class="Footer_bg">
    <div>
        <em style="color: #ffffff">
            <a href="/gioi-thieu.html" style="font-size: 15px;color: #ffffff">Giới thiệu</a> | <a href="/lien-he.html" style="font-size: 15px;color: #ffffff">Liên hệ</a>
        </em>
    </div>
    <div class="Footer">
        <em>
            Copyright © 2016 by sxmb.vn. All rights reserved.
            <br/>Chuyên trang Phân tích, soi cầu, thống kê và Tường thuật trực tiếp kết quả xổ số Miền Bắc nhanh nhất, chính xác nhất.
        </em>
    </div>
</div>
<script type="text/javascript">
    $(window).load(function ()
    {
        var today = '${today}';
        var dd = today.substring(0, 2);
        var mm = parseInt(today.substring(3, 5)) - 1;
        var yyyy = today.substring(6, 10);
        $('#mydate').glDatePicker(
                {
                    showAlways: true,
                    selectedDate: new Date(yyyy, mm, dd),
                    onClick: function (target, cell, date, data) {
                        var string = '';
                        if (eval(date.getDate()) < 10) {
                            string = "0" + date.getDate() + "-";
                        }
                        else {
                            string = date.getDate() + "-";
                        }

                        if (eval(date.getMonth() + 1) < 10) {
                            string = string + "0" + (date.getMonth() + 1) + "-";
                        }
                        else {
                            string = string + (date.getMonth() + 1) + "-";
                        }
                        string = string + date.getFullYear();
                        var url = document.URL;
                        if (string !== null && string !== "") {
                            var arrDate = string.split("-");
                            var yyyy = arrDate[2];
                            var mm = arrDate[1];
                            var dd = arrDate[0];
                            var d = new Date();
                            if (eval(yyyy) === eval(d.getUTCFullYear())) {//neu nam la hien tai
                                if (eval(mm) === eval(d.getMonth() + 1)) {
                                    if (eval(dd) <= eval(d.getDate())) {
                                        if (url.indexOf('xsmt') > -1) {
                                            //mien trung
                                            url = "/xsmt-xo-so-mien-trung-ngay-" + string + ".html";
                                            window.location.href = url;
                                        } else if (url.indexOf('xsmn') > -1) {
                                            //mien nam
                                            url = "/xsmn-xo-so-mien-nam-ngay-" + string + ".html";
                                            window.location.href = url;
                                        } else if (url.indexOf('xsmb') > -1 || url.indexOf('xo-so-mien-bac-ngay') > -1 || url.localeCompare('http://sxmb.vn') === 0 || url.localeCompare('http://sxmb.vn/') === 0 || url.localeCompare('http://localhost:8080/') === 0) {
                                            url = "/xsmb-xo-so-mien-bac-ngay-" + string + ".html";
                                            window.location.href = url;
                                        } else {
                                            if (url.indexOf('-ngay-') > -1) {
                                                var urlTemp = url.substring(url.indexOf('/', 7), url.length);
                                                var urlTemp2 = urlTemp.replace('.html', '');
                                                url = urlTemp2.substring(0, urlTemp2.indexOf('-ngay-')) + "-ngay-" + string + ".html";
                                                window.location.href = url;

                                            } else {
                                                url = "/xsmb-xo-so-mien-bac" + "-ngay-" + string + ".html";
                                                window.location.href = url;
                                            }
                                        }
                                    } else {
                                        //trường hợp ngày lớn hơn ngày hiện tại
                                        window.location.href = url;
                                    }
                                } else if (eval(mm) < eval(d.getMonth() + 1)) {//tháng ko phải tháng hiện tại
                                    if (url.indexOf('xsmt') > -1) {
                                        url = "/xsmt-xo-so-mien-trung-ngay-" + string + ".html";
                                        window.location.href = url;
                                    } else if (url.indexOf('xsmn') > -1) {
                                        url = "/xsmn-xo-so-mien-nam-ngay-" + string + ".html";
                                        window.location.href = url;
                                    } else if (url.indexOf('kqsxmb') > -1 || url.indexOf('xo-so-mien-bac-ngay') > -1 || url.localeCompare('http://sxmb.vn') === 0 || url.localeCompare('http://sxmb.vn/') === 0) {
                                        url = "/xsmb-xo-so-mien-bac-ngay-" + string + ".html";
                                        window.location.href = url;
                                    } else {
                                        if (url.indexOf('-ngay-') > -1) {
                                            var urlTemp = url.substring(url.indexOf('/', 7), url.length);
                                            var urlTemp2 = urlTemp.replace('.html', '');
                                            url = urlTemp2.substring(0, urlTemp2.indexOf('-ngay-')) + "-ngay-" + string + ".html";
                                            window.location.href = url;

                                        } else {
//                                            var urlTemp = url.substring(url.indexOf('/', 7), url.length);
//                                            var urlTemp2 = urlTemp.replace('.html', '');
                                            url = "/xsmb-xo-so-mien-bac" + "-ngay-" + string + ".html";
                                            window.location.href = url;
                                        }
                                    }
                                }
                            }
                            if (eval(yyyy) < eval(d.getUTCFullYear())) {//neu nam nho hon nam hien tai
                                if (url.indexOf('xsmt') > -1) {
                                    //mien trung
                                    url = "/xsmt-xo-so-mien-trung-ngay-" + string + ".html";
                                    window.location.href = url;
                                } else if (url.indexOf('xsmn') > -1) {
                                    //mien nam
                                    url = "/xsmn-xo-so-mien-nam-ngay-" + string + ".html";
                                    window.location.href = url;
                                } else if (url.indexOf('xsmb') > -1 || url.indexOf('xo-so-mien-bac-ngay') > -1 || url.localeCompare('http://sxmb.vn') === 0 || url.localeCompare('http://sxmb.vn/') === 0 || url.localeCompare('http://localhost:8080/') === 0) {
                                    url = "/xsmb-xo-so-mien-bac-ngay-" + string + ".html";
                                    window.location.href = url;
                                } else {
                                    if (url.indexOf('-ngay-') > -1) {
                                        var urlTemp = url.substring(url.indexOf('/', 7), url.length);
                                        var urlTemp2 = urlTemp.replace('.html', '');
                                        url = urlTemp2.substring(0, urlTemp2.indexOf('-ngay-')) + "-ngay-" + string + ".html";
                                        window.location.href = url;

                                    } else {
                                        url = "/xsmb-xo-so-mien-bac" + "-ngay-" + string + ".html";
                                        window.location.href = url;
                                    }
                                }
                            }
                        } else {
                            window.location.href = url;
                        }
                    }
                }
        );
    });
</script>