/**
 * Service layer is used for all data and business checks, plus object
 * manipulations. As it is located between repository and web layers, Service
 * Layer should make all the job excluding Web and Repository related. Most
 * services are separated on classes based on model they works with.
 *
 * In Service layer we check all security concerns: whether user performing
 * request have permissions to do that, if not, we throw Security Exception.
 * Also we transmit any technical exceptions we get from repository further.
 * Service layer should be independent from repository, so layer is written
 * without any idea about source of data.
 */
package it.sevenbits.project.application.services;