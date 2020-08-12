package com.cengiztoru.architecturalcomponentssample.util

import java.io.IOException

class ApiExceptions(message: String) : IOException(message)
class NoInternetExceptions(message: String) : IOException(message)