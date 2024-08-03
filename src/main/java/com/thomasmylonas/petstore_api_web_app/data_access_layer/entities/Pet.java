package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

public class Pet {
}


/*
Pet{
    id	        integer($int64)
    category	Category{
                    id	integer($int64)
                    name	string
                }
    name*	    string // example: doggie
    photoUrls*	[
        xml: OrderedMap { "wrapped": true }
        string
        xml: OrderedMap { "name": "photoUrl" }
        xml: name: photoUrl
       ]
    tags	[
        xml: OrderedMap { "wrapped": true }
        Tag{
            id	integer($int64)
            name	string
        }
    ]
    status	    string Enum: [ available, pending, sold ] // pet status in the store
}
*/
