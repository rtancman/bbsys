/**
 * Arquivo com as funcoes globais do sistema bodyboardsys
 * @autor Raffael Tancman
 */


var Menu = {
	
	'checaFuncao' : function(){
		opcaoEscolhida = $F('Menu');
		
		if( opcaoEscolhida == '' || typeof(opcaoEscolhida) == 'undefined' || opcaoEscolhida == 'null'){
			alert('Você precisa selecionar uma opção válida!');
		}else{
			document.location.href = opcaoEscolhida;
		}
	}
	
}

var Inscricao = {
	'selecionaCampeonato': function (retornar){

		$('loader').show();
		var valor = $F('ddCampenatoID');
		if(valor != 0){
			location.href='/Bodyboardsys/Campeonato/Inscricao/'+retornar+'?campeonatoID='+valor;
		}else{
			$('loader').hide();
			alert('Selecione uma opção válida!');
		}
	}
}

var Relatorio = {
	'ordenarTopo': function (retornar,campoID){
		
		$('loader').show();
		var valor = $F(campoID);
		
		if( valor == "" ){
			alert('Selecione uma opção válida!');
		}else{
			retornar = '/Bodyboardsys'+retornar+'?campoOrdem='+valor;
			location.href= retornar;
		}
		
	}
}

var Bateria = {
	'selecionaCampeonato': function (retornar){

		$('loader').show();
		var valor = $F('ddCampeonato');
		if(valor != 0){
			location.href='/Bodyboardsys/Campeonato/Montar/'+retornar+'?campeonatoId='+valor;
		}else{
			$('loader').hide();
			//alert('Selecione uma opção válida!');
		}
	},
	
	'selecionaEtapa': function (retornar){
			
		$('loader').show();
		var valor = $F('ddCampeonatoEtapa');

		if(valor != 0){
			location.href='/Bodyboardsys/Campeonato/Montar/'+retornar+'?campeonatoEtapaId='+valor;
		}else{
			$('loader').hide();
			//alert('Selecione uma opção válida!');
		}
	}
}

/**
 * Funcao para mover de um select para o outro
*/

function move(MenuOrigem, MenuDestino){
    var arrMenuOrigem = new Array();
    var arrMenuDestino = new Array();
    var arrLookup = new Array();
    var i;
    for (i = 0; i < MenuDestino.options.length; i++){
        arrLookup[MenuDestino.options[i].text] = MenuDestino.options[i].value;
        arrMenuDestino[i] = MenuDestino.options[i].text;
    }
    var fLength = 0;
    var tLength = arrMenuDestino.length;
    for(i = 0; i < MenuOrigem.options.length; i++){
        arrLookup[MenuOrigem.options[i].text] = MenuOrigem.options[i].value;
        if (MenuOrigem.options[i].selected && MenuOrigem.options[i].value != ""){
            arrMenuDestino[tLength] = MenuOrigem.options[i].text;
            tLength++;
        }
        else{
            arrMenuOrigem[fLength] = MenuOrigem.options[i].text;
            fLength++;
        }
    }
    arrMenuOrigem.sort();
    arrMenuDestino.sort();
    MenuOrigem.length = 0;
    MenuDestino.length = 0;
    var c;
    for(c = 0; c < arrMenuOrigem.length; c++){
        var no = new Option();
        no.value = arrLookup[arrMenuOrigem[c]];
        no.text = arrMenuOrigem[c];
        MenuOrigem[c] = no;
    }
    for(c = 0; c < arrMenuDestino.length; c++){
        var no = new Option();
        no.value = arrLookup[arrMenuDestino[c]];
        no.text = arrMenuDestino[c];
        MenuDestino[c] = no;
   }
}