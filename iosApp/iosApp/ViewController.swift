import UIKit
import app

class ViewController: UIViewController, PhotoView {
    
    lazy var presenter: PhotoPresenter = {
        PhotoPresenter(
            uiContext: UI() as KotlinCoroutineContext,
            view: self
        )
    }()
    
    var isUpdating: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //label.text = Proxy().proxyHello()
        
        presenter.onRequestData()
    }
    
    func showError(error: KotlinThrowable) {
        label.text = error.message
    }
    
    func onUpdate(data: PhotoResponse) {
        let make = data.exif?.make ?? ""
        
        label.text = "id: \(data.id)\n exif.make: \(make)"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
