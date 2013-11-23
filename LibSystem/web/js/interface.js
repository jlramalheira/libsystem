/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$('[data-searchfilter=toggle]').click(function (){
    var target = $(this).attr("data-searchfilter-target");
    $(''+target+'').toggle(300);
    $(this).toggleClass('active');
});

$('[data-rowlink] tbody tr[data-rowlink-href]').click(function(){
    var href = $(this).attr('data-rowlink-href');
    document.location = href;
});


$(document).ready(function(){
    $('select[data-type="selectsearch"]').select2({
        placeholder: "Escolha uma opção",
        allowClear: false
    });
});

