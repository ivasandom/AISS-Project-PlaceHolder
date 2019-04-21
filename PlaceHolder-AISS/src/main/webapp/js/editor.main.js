var trees = Array.from(document.getElementsByClassName("tree"));
var tree = {};

trees = trees.map(tree => JSON.constructor({
    "path": tree.getAttribute("data-path"),
    "type": tree.getAttribute("data-type"),
    "name": tree.getAttribute("data-name"),
    "size": tree.getAttribute("data-size")}));

function addnode(obj){
    var splitpath = obj.path.replace(/^\/|\/$/g, "").split('/');
    var ptr = tree;
    for (i=0;i<splitpath.length;i++)
    {
        node = { text: splitpath[i],
        type: 'directory'};
        if(i == splitpath.length-1)
        {node.size = obj.size;node.type = obj.type;}
        ptr[splitpath[i]] = ptr[splitpath[i]]||node;
        ptr[splitpath[i]].children=ptr[splitpath[i]].children||{};
        ptr=ptr[splitpath[i]].children;
    }    
}

trees.map(addnode);


function transformChildren(obj){
    var lista = [];
    for (var key in obj){
        let x = obj[key];
        x.children = transformChildren(x.children);
        lista.push(x);
    }
    return lista;
}

var fileExplorer = [];
for (var key in tree){
    let x = tree[key];
    x.children = transformChildren(x.children);
    fileExplorer.push(x);
}