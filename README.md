# spring-rest-card-verifier
Use lookup.binlist in a Spring app to verify the credit card information and return the information as JSON

## Usage

There are 2 API calls present that are both located on localhost:8080 . They are:

* /card-scheme/verify/{card} 

This returns a payload contains they type of card, brand and location.

*example:*
> /card-scheme/verify/45717360
> 
> {
>     'success':true,
>     'payload':
>     {
>         'scheme':'visa',
>         'type':'debit',
>         'bank':'Jyske Bank'
>     }
> }

* /card-scheme/stats?start={int}&limit={int}

Returns the number of times a particular card number was searched.

*example:*
> /card-scheme/stats?start=1&limit=6
> 
> {
>     'start':1,
>     'limit':3,
>     'size':1,
>     'payload':
>     {
>         '45717360':1
>     }
> }