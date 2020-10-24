function showCadIngredienteDiv(){
    
    
    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadIngredientes");
    let div2 = document.getElementById("CadIngredientes");
    
    if(div.style.display = 'none'){
        tip.style.display = 'none';
        div.style.display = 'block';
    }

}

function showCadBebidaDiv(){

    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadBebidas");
    let div2 = document.getElementById("CadIngredientes");
    
    if(div.style.display = 'none'){
        tip.style.display = 'none';
        div.style.display = 'block';
    }
    
    

}

function showInicioDiv(){

    let tip = document.getElementById("Agrupado");
    
    let div = document.getElementById("CadIngredientes");
    
    if(div.style.display = 'block'){
        tip.style.display = 'block';
        div.style.display = 'none';
    }
    
    

}