$(document).ready(function () {
    //show comment event
    $('.show-all-comments')
      .click(
        function (e) {
          var target = $(e.target).find('span');
          if (target.hasClass('show-comments')) {
            $(e.target).html('<span class="hidden-comments"></span> Nascondi altri commenti');
          } else {
            $(e.target).html('<span class="show-comments"></span> Carica altri commenti');
          }
        });

    //Active tooltip
    $('[data-toggle="tooltip"]').tooltip();

  });
