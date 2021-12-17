jQuery(window).on("load", function() {
	"use strict";

	/* -----------------------------------------
	 FlexSlider Init
	 ----------------------------------------- */
	jQuery("#slider").flexslider({
		directionNav: false,
		controlnav: true,
		start: function(slider) {
			slider.removeClass('loading');
		}
	});

	jQuery(".room-slider").flexslider({
		prevText: '',
		nextText: '',
		directionNav: true,
		controlNav: false,
		start: function(slider) {
			slider.removeClass('loading');
		}
	});

});

jQuery(document).ready(function($) {
	"use strict";

	/* -----------------------------------------
	 Main Navigation Init
	 ----------------------------------------- */
	$('ul.navigation').superfish({
		delay:       300,
		animation:   { opacity:'show', height:'show' },
		speed:       'fast',
		dropShadows: false
	});

	

	/* -----------------------------------------
	 Custom Select Boxes
	 ----------------------------------------- */
	/*var box = $(".dk");
	box.dropkick({
		theme: 'ci'
	});*/

	/* -----------------------------------------
	 Responsive videos
	 ----------------------------------------- */
	$(".video-wrap").fitVids();

	

	/* -----------------------------------------
	 Responsive Menus Init with jPanelMenu
	 ----------------------------------------- */
	$("#mobilemenu").mmenu();

	/* -----------------------------------------
	 Lightboxes
	 ----------------------------------------- */
	var $pp = $("a[data-rel^='prettyPhoto']");
	if ($pp.length) {
		$pp.prettyPhoto({
			show_title: false,
			hook: 'data-rel',
			social_tools: false,
			theme: 'pp_ignited',
			horizontal_padding: 20,
			opacity: 0.95,
			deeplinking: false
		});
	}

	/* -----------------------------------------
	 Map Init
	 ----------------------------------------- */
	if ( $("#map").length ) {
		map_init();
	}

});

function map_init() {
	'use strict';
	var myLatlng = new google.maps.LatLng(33.59,-80);
	var mapOptions = {
		zoom: 8,
		center: myLatlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};

	var map = new google.maps.Map(document.getElementById('map'), mapOptions);

	var contentString = '<div id="content">Change it to whatever your want! <strong>HTML</strong> too!</div>';

	var infowindow = new google.maps.InfoWindow({
		content: contentString
	});

	var marker = new google.maps.Marker({
		position: myLatlng,
		map: map,
		title: 'Hello'
	});
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map,marker);
	});
}
