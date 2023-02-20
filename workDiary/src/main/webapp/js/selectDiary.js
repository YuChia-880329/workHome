
	let mouseenterColor = '#BDEDFF';
	let mouseleaveColor = 'white';
	let mouseClickColor = '#87CEFA';
	let bgColorProperty = 'background-color';
	
	
	function selectDiaryTrSetting(diaryTrId, diaryBodyId, deleteBtnId, clearBtnId, seletedTrDataName){
		
		$('#' + diaryTrId).click(function(){
			
			var originalSeletedTrId = $('#' + diaryBodyId).data(seletedTrDataName);
			
			if(originalSeletedTrId != diaryTrId){
				selectDiaryTrHandler(diaryTrId, diaryBodyId, seletedTrDataName);
			}
			
			if(originalSeletedTrId == ''){
				$('#' + deleteBtnId).prop('disabled', false);
				$('#' + clearBtnId).prop('disabled', false);
			}
		});
	}
	function selectDiaryTrHandler(diaryTrId, diaryBodyId, seletedTrDataName){
		
		clickColorHandler(diaryTrId);
		

		var diaryBody = $('#' + diaryBodyId);
		var seletedTrDataName = 'seletedTr';
		var seletedTrId = diaryBody.data(seletedTrDataName);
		if(seletedTrId != ''){
			
			$('#' + seletedTrId).css(bgColorProperty, 'white');
			mouseenterChangeColor(seletedTrId);
			mouseleaveChangeColor(seletedTrId);
		}
		diaryBody.data(seletedTrDataName, diaryTrId);
	}
	
	
	
	function mouseenterChangeColor(diaryTrId){
		
		$('#' + diaryTrId).mouseenter(function(){
			
			mouseenterColorHandler(diaryTrId);
		});
	}
	function mouseleaveChangeColor(diaryTrId){
		
		$('#' + diaryTrId).mouseleave(function(){
			
			mouseleaveColorHandler(diaryTrId);
		});
	}
	
	
	function clickColorHandler(diaryTrId){
		
		var diaryTr = $('#' + diaryTrId);
		diaryTr.css(bgColorProperty, mouseClickColor);
		diaryTr.off('mouseenter');
		diaryTr.off('mouseleave');
	}
	function mouseenterColorHandler(diaryTrId){
		
		$('#' + diaryTrId).css(bgColorProperty, mouseenterColor);
	}
	function mouseleaveColorHandler(diaryTrId){
		
		$('#' + diaryTrId).css(bgColorProperty, mouseleaveColor);
	}