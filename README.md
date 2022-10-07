# jslt-microservice-demo
---
> This is a very simple example of a microservice. Most notably it has no security mechanisms to provide authentication and authorizations.

## Example Input
```json
{
	"config": {
		"id": 1,
		"name": "DeviceFeatures",
		"transforms": [{
				"name": "eventId",
				"useInML": true,
				"enabled": true,
				"jsltExpression": ".eventId"
			},
			{
				"name": "device_os",
				"useInML": true,
				"enabled": true,
				"jsltExpression": ".device.osType"
			},
			{
				"name": "device_description",
				"useInML": true,
				"enabled": true,
				"jsltExpression": ".device.osType + \" \" + .device.model"
			}
		]
	},
	"input": [{
			"eventId": "878237843",
			"device": {
				"osType": "Linux",
				"model": "Laptop"
			},
			"ip": "10.45.2.30",
			"sessionId": "ads79uoijd098098"
		},
		{
			"eventId": "878234843",
			"device": {
				"osType": "OS/2",
				"model": "Desktop"
			},
			"ip": "10.45.2.34",
			"sessionId": "ads79uoijd098097"
		}
	]
}
```

## Example Output
```json
[
    {
        "eventId": "878237843",
        "device_os": "Linux",
        "device_description": "Linux Laptop"
    },
    {
        "eventId": "878234843",
        "device_os": "OS/2",
        "device_description": "OS/2 Desktop"
    }
]
```
